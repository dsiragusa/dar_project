Security.allowDomain('*');
lso = SharedObject.getLocal("pariServices");

if (lso.data.session == null)
{
	lso.data.session = "NULL";
}

lso.data.session = String(flash.external.ExternalInterface.call("checkCookie", lso.data.session));
lso.flush();
