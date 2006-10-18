xmi2model

This project will generate a caDSR metadata extract from a semantically annotated XMI file.

You can run the converter using ant via the xmi2model target:

	ant -Dxmi=[xmi file] -Dmodel=[model file] -DprojectShortName=[the short name] -DprojectVersion=[the version] -DprojectLongName=[the long name] -DprojectDescription=[the description] xmi2model

Don't forget to put the parameters in quotes if they have spaces.

An example of this is:

	mkdir test/resources/XMIParser
	ant -Dxmi=test/resources/xmi/Annotated_fixed_tumor_registry_v1_rv1.xmi -Dmodel=test/resources/XMIParser/Annotated_fixed_tumor_registry_v1_rv1_model.xml "-DprojectShortName=Tumor Registry" -DprojectVersion=1 "-DprojectLongName=Tumor Registry" "-DprojectDescription=Tumor Registry" xmi2model
	
Limitations:

1) Does not map actual public IDs for the CDEs
2) Does not map value domains