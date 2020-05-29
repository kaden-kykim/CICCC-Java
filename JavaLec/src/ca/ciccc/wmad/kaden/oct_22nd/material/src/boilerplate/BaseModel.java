package ca.ciccc.wmad.kaden.oct_22nd.material.src.boilerplate;

public class BaseModel
	{
		BaseView view;
		BaseController controller;
		
		public void SetContoller(BaseController aController)
			{controller = aController;}
			
		public void SetView(BaseView aView)
			{view = aView;}
	}
