////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.view
{

import dz.alkhwarizmix.framework.flex.dtos.security.model.vo.UserVO;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.event.MoqawalatiEvent;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiMediator;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiLoginUserProxy;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBox;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxEvent;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٣ جمادى الأول ١٤٣٥ (March 13, 2014)
 */
public class LoginBoxMediator extends MoqawalatiMediator
	implements IMoqawalatiMediator
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	/**
	 * The mediator name
	 */
	public static const NAME:String = "LoginBoxMediator";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function LoginBoxMediator(viewComponent:Object = null)
	{
		super(NAME, viewComponent);
		
		setViewComponent(viewComponent);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static var LOG:IAlKhwarizmixLogger = null;
	
	override protected function get logger():IAlKhwarizmixLogger
	{
		if (!LOG)
			LOG = AlKhwarizmixLog.getLogger(LoginBoxMediator);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of loginBox
	 */
	public final function get loginBox():LoginBox
	{
		return viewComponent as LoginBox;
	}
	
	//----------------------------------
	//  appLoginUserProxy
	//----------------------------------
	
	public final function get appLoginUserProxy():MoqawalatiLoginUserProxy
	{
		return appFacade.retrieveProxy(MoqawalatiLoginUserProxy.NAME)
			as MoqawalatiLoginUserProxy;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function listNotificationInterests():Array
	{
		return [
			MoqawalatiConstants.LOGINUSER_PROXY_CHANGED
		];
	}
	
	/**
	 *  @inheritDoc
	 */
	override protected function handleNotification_try(notif:INotification):void
	{
		super.handleNotification_try(notif);
		
		switch (notif.getName())
		{
			case MoqawalatiConstants.LOGINUSER_PROXY_CHANGED:
			{
				handleLoginUserProxyChanged(notif.getBody());
				break;
			}
				
		} // switch
	}
	
	/**
	 *  @inheritDoc
	 */
	override public function setViewComponent(viewComponent:Object):void
	{
		if (loginBox)
			removeEventListenersFromLoginBox();
		
		super.setViewComponent(viewComponent);
		
		if (loginBox)
			addEventListenersToLoginBox();
	}
	
	private function addEventListenersToLoginBox():void
	{
		loginBox.addEventListener(LoginBoxEvent.CONNECT, loginBox_connectHandler);
		loginBox.addEventListener(LoginBoxEvent.SUBSCRIBE, loginBox_subscribeHandler);
		loginBox.addEventListener(LoginBoxEvent.LOGIN, loginBox_loginHandler);
		loginBox.addEventListener(LoginBoxEvent.LOGOUT, loginBox_logoutHandler);
	}
	
	private function removeEventListenersFromLoginBox():void
	{
		loginBox.removeEventListener(LoginBoxEvent.LOGIN, loginBox_loginHandler);
		loginBox.removeEventListener(LoginBoxEvent.LOGOUT, loginBox_logoutHandler);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of handleLoginUserProxyChanged
	 */
	private function handleLoginUserProxyChanged(notifBody:Object):void
	{
		logger.debug("handleLoginUserProxyChanged");
		
		switch (loginBox.btnSend.labelResKey)
		{
			case "CONNECT":
			{
				loginBox.loggedUser = null;
				loginBox.connectedUser = appLoginUserProxy.user;
				break;
			}
			
			case "SUBSCRIBE":
			case "LOGIN":
			case "LOGOUT":
			{
				loginBox.loggedUser = appLoginUserProxy.user;
				break;
			}
			
		}
	}
	
	//--------------------------------------------------------------------------
	//
	//  Event handlers
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	private function loginBox_connectHandler(event:MoqawalatiEvent):void
	{
		logger.debug("loginBox_connectHandler");
		
		var userToConnect:UserVO = new UserVO();
		userToConnect.userId = loginBox.textUserId.text;
		sendNotification(MoqawalatiConstants.CONNECT,
			{
				operationParams : [userToConnect]
			});
	}
	
	/**
	 * @private
	 */
	private function loginBox_loginHandler(event:MoqawalatiEvent):void
	{
		logger.debug("loginBox_loginHandler");
		
		var userToLogin:UserVO = new UserVO();
		userToLogin.userId = loginBox.textUserId.text;
		var password:String = loginBox.textPassword.text;
		sendNotification(MoqawalatiConstants.LOGIN,
			{
				operationParams : [userToLogin, password]
			});
	}
	
	/**
	 * @private
	 */
	private function loginBox_logoutHandler(event:MoqawalatiEvent):void
	{
		logger.debug("loginBox_logoutHandler");
		
		sendNotification(MoqawalatiConstants.LOGOUT,
			{
				operationParams : [loginBox.loggedUser]
			});
	}
	
	/**
	 * @private
	 */
	private function loginBox_subscribeHandler(event:MoqawalatiEvent):void
	{
		logger.debug("loginBox_subscribeHandler");
		
		var userToSubscribe:UserVO = new UserVO();
		userToSubscribe.userId = loginBox.textUserId.text;
		userToSubscribe.name = loginBox.textUserName.text;
		sendNotification(MoqawalatiConstants.SUBSCRIBE,
			{
				operationParams : [userToSubscribe]
			});
	}
	
} // class
} // package
