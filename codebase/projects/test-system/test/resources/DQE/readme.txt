Here are the following queries that are tested:

===============
= Basic Tests =
===============

CAE

	0. Give me all the participants in CAE
	1. Give me all the participants in CAE with a Nottingham Histopathologic Grade greater than 1
	2. Give me all the participants in CAE with greatestDimension greater than 1 and additionalDimensionZ greater than 3

caTissue CORE

	3. Give me all the tissue specimens in caTissue CORE
	4. Give me all the breast tissue specimens in caTissue CORE
	5. Give me all the inactive participants in in caTissue CORE that have a tissue specimen

caTissue CORE, CAE

	6. Give me all the active participants with tissue specimens that have a three dimensional size greater than 2
	7. Give me all the active participants with tissue specimens that have a Nottingham Histopathologic Grade greater than 1

=============
= Use Cases =
=============

A. Find Available Tumor Tissue (partial)

	8. Give me all the available breast tissue
	9. Give me all the available breast tissue from patients that are ER positive

B. Find factors of survival (none)

C. Determine the distribution of disease factors over time (none)

D. Find patients for trials (complete)

	10. Give my all the patients that are triple negative for breast pathology biomarkers

E. Determine correlation of factors pre and post surgery (none)

