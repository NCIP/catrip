﻿//------------------------------------------------------------------------------
// <autogenerated>
//     This code was generated by a tool.
//     Runtime Version: 1.1.4322.2032
//
//     Changes to this file may cause incorrect behavior and will be lost if 
//     the code is regenerated.
// </autogenerated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by Microsoft.VSDesigner, Version 1.1.4322.2032.
// 
namespace catrip_1._0_deid_csharp.WebReference {
    using System.Diagnostics;
    using System.Xml.Serialization;
    using System;
    using System.Web.Services.Protocols;
    using System.ComponentModel;
    using System.Web.Services;
    
    
    /// <remarks/>
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Web.Services.WebServiceBindingAttribute(Name="DeIdServiceSOAP11Binding", Namespace="http://ws.apache.org/axis2")]
    public class DeIdService : System.Web.Services.Protocols.SoapHttpClientProtocol {
        
        public security securityValue;
        
        /// <remarks/>
        public DeIdService() {
            this.Url = "https://catrip1.duhs.duke.edu:8443/axis2/services/DeIdService";
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapHeaderAttribute("securityValue")]
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("urn:deid", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Bare)]
        [return: System.Xml.Serialization.XmlElementAttribute("deidResponse", Namespace="http://deid.catrip.cabig.duke.edu/xsd")]
        public deidResponse deid([System.Xml.Serialization.XmlElementAttribute(Namespace="http://deid.catrip.cabig.duke.edu/xsd")] security security, [System.Xml.Serialization.XmlElementAttribute("deid", Namespace="http://deid.catrip.cabig.duke.edu/xsd")] deid deid1) {
            object[] results = this.Invoke("deid", new object[] {
                        security,
                        deid1});
            return ((deidResponse)(results[0]));
        }
        
        /// <remarks/>
        public System.IAsyncResult Begindeid(security security, deid deid1, System.AsyncCallback callback, object asyncState) {
            return this.BeginInvoke("deid", new object[] {
                        security,
                        deid1}, callback, asyncState);
        }
        
        /// <remarks/>
        public deidResponse Enddeid(System.IAsyncResult asyncResult) {
            object[] results = this.EndInvoke(asyncResult);
            return ((deidResponse)(results[0]));
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://deid.catrip.cabig.duke.edu/xsd")]
    [System.Xml.Serialization.XmlRootAttribute(Namespace="http://deid.catrip.cabig.duke.edu/xsd", IsNullable=false)]
    public class security : System.Web.Services.Protocols.SoapHeader {
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string user;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string password;
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://deid.catrip.cabig.duke.edu/xsd")]
    public class deidResponse {
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string @return;
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://deid.catrip.cabig.duke.edu/xsd")]
    public class deid {
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string phi;
    }
}
