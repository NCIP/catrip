using System;
using System.Net;
using System.Security.Cryptography.X509Certificates;

using catrip_1._0_deid_csharp.WebReference;

namespace catrip_1._0_deid_csharp
{
	internal class AcceptAllCertificatePolicy : ICertificatePolicy
	{
		public AcceptAllCertificatePolicy()
		{
		}

		public bool CheckValidationResult(ServicePoint sPoint,
			X509Certificate cert, WebRequest wRequest,int certProb)
		{
			// Always accept
			return true;
		}
	}

	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class Class1
	{
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args)
		{
			ServicePointManager.CertificatePolicy = new AcceptAllCertificatePolicy();

			DeIdService service = new DeIdService();
			
			security security = new security();
			security.user = "chris.hubbard";
			security.password = "C4r1sHu66ard";
			service.securityValue = security;

			deid input = new deid();
			input.phi = "hi there";

			deidResponse output = new deidResponse();

			try 
			{
				output = service.deid(null, input); 
				Console.WriteLine(output.@return);
			} 
			catch (Exception e) 
			{
				Console.WriteLine(e.Message);
				Console.WriteLine(e.StackTrace);
				Console.WriteLine(e.InnerException);
			}
		}
	}
}
