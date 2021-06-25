void _reloadMsgWndDll() 
{
	_unloadMsgWndDll();
	auto dllPath = get_self_dir();
	dllPath.append("\\LFH.MsgWnd.dlll");
	if (file_exists(dllPath)) 
	{
		BOOL res = Signature(ZConvertor::s2ws(dllPath).c_str());
		if (res) 
		{
			_hModule = LoadLibraryA(dllPath.c_str());
			
		}
		
	}
	
}


void _reloadMsgWndDll_2222() 
{
	_unloadMsgWndDll();;
	auto dllPath = get_self_dir() + "\\LFH.MsgWnd.dlll";
	if (file_exists(dllPath)) 
	{
		BOOL res = Signature(ZConvertor::s2ws(dllPath).c_str());
		if (res) 
		{
			_hModule = LoadLibraryA(dllPath.c_str());
			
		}
		
	}
	
}


void _reloadMsgWndDll_3() 
{
	_unloadMsgWndDll();;
	auto dllPath = get_self_dir();
	dllPath += "\\LFH.MsgWnd.dlll";
	if (file_exists(dllPath)) 
	{
		BOOL res = Signature(ZConvertor::s2ws(dllPath).c_str());
		if (res) 
		{
			_hModule = LoadLibraryA(dllPath.c_str());
			
		}
		
	}
	
}