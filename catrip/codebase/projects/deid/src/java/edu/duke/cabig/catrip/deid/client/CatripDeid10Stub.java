
        /**
        * CatripDeid10Stub.java
        *
        * This file was auto-generated from WSDL
        * by the Apache Axis2 version: 1.0 May 05, 2006 (12:31:13 IST)
        */
        package edu.duke.cabig.catrip.deid.client;

import org.apache.axiom.soap.SOAPHeaderBlock;

        

        /*
        *  CatripDeid10Stub java implementation
        */

        
        public class CatripDeid10Stub extends org.apache.axis2.client.Stub
        {
        //default axis home being null forces the system to pick up the mars from the axis2 library
        public static final java.lang.String AXIS2_HOME = null;
        protected static org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExeptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExeptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private String user;
        private String password;
	
    private void populateAxisService(){

        //creating the Service
        _service = new org.apache.axis2.description.AxisService("CatripDeid10");
	

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;
	


        _operations = new org.apache.axis2.description.AxisOperation[1];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("", "deid"));

	    

            _operations[0]=__operation;
            _service.addOperation(__operation);
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

     public CatripDeid10Stub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint)
        throws java.lang.Exception {
         //To populate AxisService
         populateAxisService();
         populateFaults();
	
	
        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        
            //Set the soap version
            _serviceClient.getOptions().setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
        
    }

    /**
     * Default Constructor
     */
    public CatripDeid10Stub() throws java.lang.Exception {
        
                    this("http://192.168.15.3:8080/axis2/services/catrip-deid-1.0" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public CatripDeid10Stub(java.lang.String targetEndpoint) throws java.lang.Exception {
        this(org.apache.axis2.context.ConfigurationContextFactory.createConfigurationContextFromFileSystem(AXIS2_HOME,null),
                targetEndpoint);
    }



        
                    /**
                    * Auto generated method signature
                    * @see edu.duke.cabig.catrip.deid.client.CatripDeid10#deid
                        * @param param2
                    
                    */
                    public edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse deid(
                    edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid param2)
                    throws java.rmi.RemoteException
                    
                    {
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("urn:deid");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                    //Style is Doc.
                                    
                                    
                                                 env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                param2,
                                                optimizeContent(new javax.xml.namespace.QName("",
                                                "deid")));
                                            
                                         		SOAPHeaderBlock header = env.getHeader().addHeaderBlock("security", env.getNamespace());
                                         		if (user != null) header.addAttribute("user", user, null);
                                         		if (password != null) header.addAttribute("password", password, null);

        // create message context with that soap envelope
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext() ;
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        System.out.println(_messageContext.getEnvelope());

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                           java.lang.Object object = fromOM(
                                        getElement(_returnEnv,"document"),
                                        edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse.class,
                                         getEnvelopeNamespaces(_returnEnv));
                           _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                           return (edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse)object;
                    
         }catch(org.apache.axis2.AxisFault f){
            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExeptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExeptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.rmi.RemoteException ex=
                                (java.rmi.RemoteException)exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw ex;
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
        }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * @see edu.duke.cabig.catrip.deid.client.CatripDeid10#startdeid
                    * @param param2
                
                */
                public  void startdeid(
                edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid param2,final edu.duke.cabig.catrip.deid.client.CatripDeid10CallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("urn:deid");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

          

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
                    
                                    //Style is Doc.
                                    
                                                 env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), param2, optimizeContent(new javax.xml.namespace.QName("", "deid")));
                                         		

        // create message context with that soap envelope
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext() ;
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                           _operationClient.setCallback(new org.apache.axis2.client.async.Callback() {
                    public void onComplete(
                            org.apache.axis2.client.async.AsyncResult result) {
                        java.lang.Object object = fromOM(getElement(
                                result.getResponseEnvelope(), "document"),
                               edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse.class,
                               getEnvelopeNamespaces(result.getResponseEnvelope())
                            );
                        callback.receiveResultdeid((edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse) object);
                    }

                    public void onError(java.lang.Exception e) {
                        callback.receiveErrordeid(e);
                    }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                

       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getName());
        }
       return returnMap;
    }

    
	
    private javax.xml.namespace.QName[] opNameArray = null;
	private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;   
			}
		}
		return false;
	}


     
   

    //http://192.168.15.3:8080/axis2/services/catrip-deid-1.0
        public static class Deid
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://deid.catrip.cabig.duke.edu/xsd",
                "deid",
                "ns1");

            


            /**
            * field for Phi
            */

            protected java.lang.String localPhi ;
           
           

           /**
           * Auto generated getter method
           * @return java.lang.String
           */
           public  java.lang.String getPhi(){
               return localPhi;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param Phi
                   */
                   public void setPhi(java.lang.String param){
                    
                   this.localPhi=param;
                   }
                

        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName){


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                             elementList.add(new javax.xml.namespace.QName("",
                                                                      "phi"));
                            
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPhi));
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }



     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{


        // This is horrible, but the OM implementation of getElementText() does not obey the proper contract.  Specifically, it does
        // does not advance the reader to the END_ELEMENT.  This bug is triggered by calls to getElementText() unpredictably, e.g. it
        // happens with outer (document) elements, but not with inner elements.  The root bug is in OMStAXWrapper.java, which is now part
        // of commons and so cannot just be fixed in axis2.  This method should be removed and the calls to it below replaced with
        // simple calls to getElementText() as soon as this serious bug can be fixed.

        private static java.lang.String getElementTextProperly(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            java.lang.String value = reader.getElementText();
            while (!reader.isEndElement())
                reader.next();
            return value;
        }

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Deid parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Deid object = new Deid();
            int event;
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                    
                    reader.next();
                            
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","phi").equals(reader.getName())){
                            
                                    java.lang.String content = getElementTextProperly(reader);
                                    object.setPhi(
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertTostring(content));
                                      
                                        reader.next();
                                    

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                            


            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
        public static class DeidResponse
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://deid.catrip.cabig.duke.edu/xsd",
                "deidResponse",
                "ns1");

            


            /**
            * field for _return
            */

            protected java.lang.String local_return ;
           
           

           /**
           * Auto generated getter method
           * @return java.lang.String
           */
           public  java.lang.String get_return(){
               return local_return;
           }

           
            
                    /**
                   * Auto generated setter method
                   * @param param _return
                   */
                   public void set_return(java.lang.String param){
                    
                   this.local_return=param;
                   }
                

        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName){


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                             elementList.add(new javax.xml.namespace.QName("",
                                                                      "return"));
                            
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_return));
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }



     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{


        // This is horrible, but the OM implementation of getElementText() does not obey the proper contract.  Specifically, it does
        // does not advance the reader to the END_ELEMENT.  This bug is triggered by calls to getElementText() unpredictably, e.g. it
        // happens with outer (document) elements, but not with inner elements.  The root bug is in OMStAXWrapper.java, which is now part
        // of commons and so cannot just be fixed in axis2.  This method should be removed and the calls to it below replaced with
        // simple calls to getElementText() as soon as this serious bug can be fixed.

        private static java.lang.String getElementTextProperly(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            java.lang.String value = reader.getElementText();
            while (!reader.isEndElement())
                reader.next();
            return value;
        }

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static DeidResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            DeidResponse object = new DeidResponse();
            int event;
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                    
                    reader.next();
                            
                                    while (!reader.isStartElement() && !reader.isEndElement())
                                        reader.next();
                                
                            if (reader.isStartElement() && new javax.xml.namespace.QName("","return").equals(reader.getName())){
                            
                                    java.lang.String content = getElementTextProperly(reader);
                                    object.set_return(
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertTostring(content));
                                      
                                        reader.next();
                                    

                              }  // End of if for expected property start element

                            
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new java.lang.RuntimeException("Unexpected subelement " + reader.getLocalName());
                                }
                            


            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
                private static javax.xml.namespace.QName[] qNameArray = {
                
                };
            
                    private  org.apache.axiom.om.OMElement  toOM(edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid param, boolean optimizeContent){
                        if (param instanceof org.apache.axis2.databinding.ADBBean){
                            org.apache.axiom.om.impl.builder.StAXOMBuilder builder
                                       = new org.apache.axiom.om.impl.builder.StAXOMBuilder
                            (org.apache.axiom.om.OMAbstractFactory.getOMFactory(),
                               new org.apache.axis2.util.StreamWrapper(param.getPullParser(edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid.MY_QNAME)));
                            org.apache.axiom.om.OMElement documentElement = builder.getDocumentElement();
                            ((org.apache.axiom.om.impl.OMNodeEx) documentElement).setParent(null); // remove the parent link
                            return documentElement;
                        }else{
                           
                           //todo finish this onece the bean serializer has the necessary methods
                            return null;
                        }
                    }

                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid param, boolean optimizeContent){
                        if (param instanceof org.apache.axis2.databinding.ADBBean){
                            org.apache.axis2.databinding.ADBSOAPModelBuilder builder = new
                                    org.apache.axis2.databinding.ADBSOAPModelBuilder(param.getPullParser(edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid.MY_QNAME),
                                                                                     factory);
                            return builder.getEnvelope();
                        }else{
                           
                           //todo finish this onece the bean serializer has the necessary methods
                            return null;
                        }
                    }
                
                    private  org.apache.axiom.om.OMElement  toOM(edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse param, boolean optimizeContent){
                        if (param instanceof org.apache.axis2.databinding.ADBBean){
                            org.apache.axiom.om.impl.builder.StAXOMBuilder builder
                                       = new org.apache.axiom.om.impl.builder.StAXOMBuilder
                            (org.apache.axiom.om.OMAbstractFactory.getOMFactory(),
                               new org.apache.axis2.util.StreamWrapper(param.getPullParser(edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse.MY_QNAME)));
                            org.apache.axiom.om.OMElement documentElement = builder.getDocumentElement();
                            ((org.apache.axiom.om.impl.OMNodeEx) documentElement).setParent(null); // remove the parent link
                            return documentElement;
                        }else{
                           
                           //todo finish this onece the bean serializer has the necessary methods
                            return null;
                        }
                    }

                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse param, boolean optimizeContent){
                        if (param instanceof org.apache.axis2.databinding.ADBBean){
                            org.apache.axis2.databinding.ADBSOAPModelBuilder builder = new
                                    org.apache.axis2.databinding.ADBSOAPModelBuilder(param.getPullParser(edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse.MY_QNAME),
                                                                                     factory);
                            return builder.getEnvelope();
                        }else{
                           
                           //todo finish this onece the bean serializer has the necessary methods
                            return null;
                        }
                    }
                

           /**
           *  get the default envelope
           */
           private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
                return factory.getDefaultEnvelope();
           }


            private  java.lang.Object fromOM(
            org.apache.axiom.om.OMElement param,
            java.lang.Class type,
            java.util.Map extraNamespaces){

                try {
                       
                      if (edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid.class.equals(type)){
                           return edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.Deid.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                      }
                              
                      if (edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse.class.equals(type)){
                           return edu.duke.cabig.catrip.deid.client.CatripDeid10Stub.DeidResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                      }
                              
                } catch (Exception e) {
                     throw new RuntimeException(e);
                }

                return null;
            }

			public String getPassword()
			{
				return password;
			}

			public void setPassword(String password)
			{
				this.password = password;
			}

			public String getUser()
			{
				return user;
			}

			public void setUser(String user)
			{
				this.user = user;
			}

        
   }
   