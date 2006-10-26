package gov.nih.nci.cagrid.data.cql.cacore;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Group;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlquery.QueryModifier;
import gov.nih.nci.cagrid.data.QueryProcessingException;

import java.util.HashMap;
import java.util.Map;

/** 
 *  CQL2HQL
 *  Translates a CQL query to Hibernate v3 HQL
 * 
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 * 
 * @created Jul 19, 2006 
 * @version $Id: LocalCQL2HQL.java,v 1.1 2006-10-26 19:33:15 srakkala Exp $ 
 */
public class LocalCQL2HQL {
	public static final String TARGET_ALIAS = "xxTargetAliasxx";
	
	private static Map predicateValues;
        
        private String dialect;
        
        public LocalCQL2HQL(String dialect){
            this.dialect = dialect;
        }

	/**
	 * Translates a CQL query into an HQL string.  This translation process assumes the
	 * CQL Query has passed validation.  Processing of invalid CQL may or may not procede
	 * with undefined results.
	 * 
	 * @param query
	 * 		The CQL Query to translate into HQL
	 * @param eliminateSubclasses
	 * 		A flag indicating that the query should be formulated to avoid
	 * 		returning subclass instances of the targeted class.
	 * @return
	 * @throws QueryProcessingException
	 */
	public String translate(CQLQuery query, boolean eliminateSubclasses) throws QueryProcessingException {
		StringBuilder hql = new StringBuilder();
		if (query.getQueryModifier() != null) {
			if (eliminateSubclasses) {
				throw new QueryProcessingException("HQL cannot use the class property when processing projection queries.");
			}
			processModifiedQuery(hql, query.getQueryModifier(), query.getTarget());
		} else {
			processTarget(hql, query.getTarget(), eliminateSubclasses);
		}
		return hql.toString();
	}
	
	
	/**
	 * Processes a query with Query Modifications applied to it
	 * 
	 * @param hql
	 * 		The HQL built thus far
	 * @param mods
	 * 		The modifications to the query
	 * @param target
	 * 		The target object
	 * @throws QueryProcessingException
	 */
	private void processModifiedQuery(StringBuilder hql, QueryModifier mods, Object target) throws QueryProcessingException {
		if (mods.isCountOnly()) {
			processCountingQuery(hql, mods, target);
		} else {
			processAttributeQuery(hql, mods, target);
		}
	}
	
	
	/**
	 * Processes a query which returns a count
	 * 
	 * @param hql
	 * 		The HQL query fragment
	 * @param mods
	 * 		The modifications to apply to the query
	 * @param target
	 * 		The query's target object
	 * @throws QueryProcessingException
	 */
	private void processCountingQuery(StringBuilder hql, QueryModifier mods, Object target) throws QueryProcessingException {
		hql.append("select count(");
		if (mods.getDistinctAttribute() != null) {
			// counting distinct attributes
			hql.append("distinct ").append(TARGET_ALIAS);
			hql.append(".").append(mods.getDistinctAttribute()).append(") ");
			processTarget(hql, target, false);
		} else if (mods.getAttributeNames() != null) {
			// counting objects where any one of the attribs is not null
			hql.append("*) ");
			// process the target object normally
			processTarget(hql, target, false);
			// only add a where statement if the target has no child restrictions
			boolean addWhereStatement = target.getAssociation() == null 
				&& target.getAttribute() == null && target.getGroup()== null;
			if (addWhereStatement) {
				hql.append(" where ");
			} else {
				hql.append(" and ");
			}
			// build the attribute not null clause
			StringBuilder attribClause = new StringBuilder();
			attribClause.append("(");
			for (int i = 0; i < mods.getAttributeNames().length; i++) {
				attribClause.append(TARGET_ALIAS).append(".");
				attribClause.append(mods.getAttributeNames(i));
				attribClause.append(" is not null");
				if (i + 1 < mods.getAttributeNames().length) {
					attribClause.append(" or ");
				}
			}
			attribClause.append(")");
			// append the attribute not null clause to the target
			hql.append(attribClause.toString());
		} else {
			// counting unique objects
			// need to use an alias in the count clause
			hql.append(TARGET_ALIAS).append(") ");
			processTarget(hql, target, false);
		}
	}
	
	
	/**
	 * Processes a query which returns attributes (distinct or otherwise)
	 * 
	 * @param hql
	 * 		The existing HQL fragment
	 * @param mods
	 * 		The modifications to apply to the query
	 * @param target
	 * 		The target object of the query
	 * @throws QueryProcessingException
	 */
	private void processAttributeQuery(StringBuilder hql, QueryModifier mods, Object target) throws QueryProcessingException {
		if (mods.getDistinctAttribute() != null) {
			// counting distinct attributes
			hql.append("select distinct ").append(TARGET_ALIAS).append(".").append(mods.getDistinctAttribute());
		} else {
			String[] names = mods.getAttributeNames();
			if (names != null) {
				hql.append("select ");
				for (int i = 0; i < names.length; i++) {
					hql.append(TARGET_ALIAS).append(".").append(names[i]);
					if (i + 1 < names.length) {
						hql.append(", ");
					}
				}
			}
		}
		hql.append(" ");
		processTarget(hql, target, false);
	}
	
	
	private void processTarget(StringBuilder hql, Object target, boolean eliminateSubclasses) 
		throws QueryProcessingException {
		String objName = target.getName();
		hql.append("From ").append(objName);
		hql.append(" as ").append(TARGET_ALIAS);
		if (eliminateSubclasses) {			
			hql.append(" where ").append(TARGET_ALIAS).append(".class = ").append(objName);
		}
		if (target.getAttribute() != null) {
			if (eliminateSubclasses) {
				hql.append(" and ");
			} else {
				hql.append(" where ");
			}
			processAttribute(hql, target.getAttribute(), true);
		}
		if (target.getAssociation() != null) {
			if (eliminateSubclasses) {
				hql.append(" and ");
			} else {
				hql.append(" where ");
			}
			processAssociation(hql, objName, target.getAssociation(), true);
		}
		if (target.getGroup() != null) {
			if (eliminateSubclasses) {
				hql.append(" and ");
			} else {
				hql.append(" where ");
			}
			processGroup(hql, objName, target.getGroup(), true);
		}
	}
	
	
	/**
	 * Processes an Object of a CQL Query.
	 * 
	 * @param hql
	 * 		The existing HQL query fragment
	 * @param obj
	 * 		The object to process into HQL
	 * @throws QueryProcessingException
	 */
	private void processObject(StringBuilder hql, Object obj) throws QueryProcessingException {
		String objName = obj.getName();
		hql.append("select id From ").append(objName);
		if (obj.getAttribute() != null) {
			hql.append(" where ");
			processAttribute(hql, obj.getAttribute(), false);
		}
		if (obj.getAssociation() != null) {
			hql.append(" where ");
			processAssociation(hql, objName, obj.getAssociation(), false);
		}
		if (obj.getGroup() != null) {
			hql.append(" where ");
			processGroup(hql, objName, obj.getGroup(), false);
		}
	}
	
	
	/**
	 * Proceses an Attribute of a CQL Query.
	 * 
	 * @param hql
	 * 		The existing HQL query fragment
	 * @param attrib
	 * 		The attribute to process into HQL
	 * @throws QueryProcessingException
	 */
	private void processAttribute(StringBuilder hql, Attribute attrib, boolean useAlias) throws QueryProcessingException {
		//if (useAlias) {
		//	hql.append(TARGET_ALIAS).append(".");
		//}
                
		//hql.append(attrib.getName());
		Predicate predicate = attrib.getPredicate();
                boolean oracle = false;
                if (dialect.indexOf("Oracle") > 0){
                    oracle = true;
                }

                if (oracle) {
                    if (predicate.equals(Predicate.EQUAL_TO) || 
                        predicate.equals(Predicate.LIKE) || 
                        predicate.equals(Predicate.NOT_EQUAL_TO)) {
                            hql.append("lower(");
                                if (useAlias) {
                                        hql.append(TARGET_ALIAS).append(".");
                                }        
                            hql.append(attrib.getName());
                            hql.append(")");                    
                    } else {
                        if (useAlias) {
                                hql.append(TARGET_ALIAS).append(".");
                        } 
                        hql.append(attrib.getName()); 
                    }
                } else {
                    hql.append(attrib.getName()); 
                }
      
                
		// unary predicates
		if (predicate.equals(Predicate.IS_NULL)) {
			hql.append(" is null");
		} else if (predicate.equals(Predicate.IS_NOT_NULL)) {
			hql.append(" is not null");

		} else if (oracle) {
                    if (predicate.equals(Predicate.EQUAL_TO) || 
                        predicate.equals(Predicate.LIKE) || 
                        predicate.equals(Predicate.NOT_EQUAL_TO)) {
                            String predValue = convertPredicate(predicate);
                            hql.append(" ").append(predValue).append(" lower('").append(attrib.getValue()).append("')");
                   } else {
                        String predValue = convertPredicate(predicate);
                        hql.append(" ").append(predValue).append(" '").append(attrib.getValue()).append("'");                       
                    }                    
                } else {
                    String predValue = convertPredicate(predicate);
                    hql.append(" ").append(predValue).append(" '").append(attrib.getValue()).append("'");                     
                }
	}
	
	
	/**
	 * Processes an Association of a CQL Query.
	 * 
	 * @param hql
	 * 		The existing HQL query fragment
	 * @param parentName
	 * 		The class name of the parent object
	 * @param assoc
	 * 		The association to process into HQL
	 * @throws QueryProcessingException
	 */
	private void processAssociation(StringBuilder hql, String parentName, Association assoc, boolean useAlias) throws QueryProcessingException {
		// get the role name of the association
		String roleName = ClassAccessUtilities.getRoleName(parentName, assoc);
		if (roleName == null) {
			// still null?? no association to the object!
			throw new QueryProcessingException("Association from type " + parentName + 
				" to type " + assoc.getName() + " does not exist.  Use only direct associations");
		}
		// make an HQL subquery for the object
		if (useAlias) {
			hql.append(TARGET_ALIAS).append(".");
		}
		hql.append(roleName).append(".id in (");
		processObject(hql, assoc);
		hql.append(")");
	}
	
	
	/**
	 * Processes a Group of a CQL Query.
	 * 
	 * @param hql
	 * 		The existing HQL query fragment
	 * @param parentName
	 * 		The type name of the parent object
	 * @param group
	 * 		The group to process into HQL
	 * @throws QueryProcessingException
	 */
	private void processGroup(StringBuilder hql, String parentName, Group group, boolean useAlias) throws QueryProcessingException {
		String logic = convertLogicalOperator(group.getLogicRelation());
		
		// flag indicating a logic clause is needed before adding further query parts
		boolean logicClauseNeeded = false;
		
		// attributes
		if (group.getAttribute() != null) {
			for (int i = 0; i < group.getAttribute().length; i++) {
				logicClauseNeeded = true;
				processAttribute(hql, group.getAttribute(i), useAlias);
				if (i + 1 < group.getAttribute().length) {
					hql.append(" ").append(logic).append(" ");
				}
			}
		}
		
		// associations
		if (group.getAssociation() != null) {
			if (logicClauseNeeded) {
				hql.append(" ").append(logic).append(" ");
			}
			for (int i = 0; i < group.getAssociation().length; i++) {
				logicClauseNeeded = true;
				processAssociation(hql, parentName, group.getAssociation(i), useAlias);
				if (i + 1 < group.getAssociation().length) {
					hql.append(" ").append(logic).append(" ");
				}
			}
		}
		
		// subgroups
		if (group.getGroup() != null) {
			if (logicClauseNeeded) {
				hql.append(" ").append(logic).append(" ");
			}
			for (int i = 0; i < group.getGroup().length; i++) {
				hql.append("( ");
				processGroup(hql, parentName, group.getGroup(i), useAlias);
				hql.append(" )");
				if (i + 1 < group.getGroup().length) {
					hql.append(" ").append(logic).append(" ");
				}
			}
		}
	}
	
	
	/**
	 * Converts a predicate to its HQL string equivalent.
	 * 
	 * @param p
	 * @return
	 */
	private String convertPredicate(Predicate p) {
		if (predicateValues == null) {
			predicateValues = new HashMap();
			predicateValues.put(Predicate.EQUAL_TO, "=");
			predicateValues.put(Predicate.GREATER_THAN, ">");
			predicateValues.put(Predicate.GREATER_THAN_EQUAL_TO, ">=");
			predicateValues.put(Predicate.LESS_THAN, "<");
			predicateValues.put(Predicate.LESS_THAN_EQUAL_TO, "<=");
			predicateValues.put(Predicate.LIKE, "LIKE");
			predicateValues.put(Predicate.NOT_EQUAL_TO, "!=");
		}
		return (String) predicateValues.get(p);
	}
	
	
	/**
	 * Converts a logical operator to its HQL string equiavalent.
	 * 
	 * @param op
	 * @return
	 */
	private String convertLogicalOperator(LogicalOperator op) throws QueryProcessingException {
		if (op.getValue().equals(LogicalOperator._AND)) {
			return "AND";
		} else if (op.getValue().equals(LogicalOperator._OR)) {
			return "OR";
		}
		throw new QueryProcessingException("Logical operator '" + op.getValue() + "' is not recognized.");
	}
        
        public static void main (String[] args) {
            String dialect = "org.hibernate.dialect.Oracle9Dialect";
            
            System.out.println(dialect.indexOf("MySQL"));
            
        }
}
