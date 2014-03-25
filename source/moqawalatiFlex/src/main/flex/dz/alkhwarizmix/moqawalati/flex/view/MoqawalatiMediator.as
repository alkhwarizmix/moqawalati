////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.view
{
	
	import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
	import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
	import dz.alkhwarizmix.framework.flex.view.AlKhwarizmixMediator;
	import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
	import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiMediator;
	import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;
	import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiCustomDataProxy;
	
	import org.puremvc.as3.multicore.interfaces.IFacade;
	import org.puremvc.as3.multicore.interfaces.INotification;
	import org.puremvc.as3.multicore.patterns.facade.Facade;
	
	/**
	 *  <p>
	 *  TODO: ASDOC
	 *  </p>
	 * 
	 *  @author فارس بلحواس (Fares Belhaouas)
	 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
	 */
	public class MoqawalatiMediator extends AlKhwarizmixMediator
		implements IMoqawalatiMediator
	{
		//--------------------------------------------------------------------------
		//
		//  Constructor
		//
		//--------------------------------------------------------------------------
		
		/**
		 *  Constructor.
		 */
		public function MoqawalatiMediator(
			mediatorName:String = null, viewComponent:Object = null)
		{
			super(mediatorName, viewComponent);
		}
		
		//--------------------------------------------------------------------------
		//
		//  Logger
		//
		//--------------------------------------------------------------------------
		
		private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
			getLogger(MoqawalatiMediator);
		
		override protected function get logger():IAlKhwarizmixLogger { return LOG; }
		
		//--------------------------------------------------------------------------
		//
		//  Properties
		//
		//--------------------------------------------------------------------------
		
		//----------------------------------
		//  appFacade
		//----------------------------------
		
		public final function get appFacade():IFacade
		{
			return Facade.getInstance(MoqawalatiConstants.FACADE_NAME);
		}
		
		//----------------------------------
		//  appConfigProxy
		//----------------------------------
		
		public final function get appConfigProxy():MoqawalatiConfigProxy
		{
			return appFacade.retrieveProxy(MoqawalatiConfigProxy.NAME)
				as MoqawalatiConfigProxy;
		}
		
		//----------------------------------
		//  appCustomDataProxy
		//----------------------------------
		
		public final function get appCustomDataProxy():MoqawalatiCustomDataProxy
		{
			return appFacade.retrieveProxy(MoqawalatiCustomDataProxy.NAME)
				as MoqawalatiCustomDataProxy;
		}
		
		//--------------------------------------------------------------------------
		//
		//  Overriden methods
		//
		//--------------------------------------------------------------------------
		
		/**
		 *  @inheritDoc
		 */
		override protected function handleNotification_catch_error(
			notif:INotification, error:Error):void
		{
			super.handleNotification_catch_error(notif, error);
		}
		
		/**
		 *  @inheritDoc
		 */
		override protected function handleNotification_finally(
			notif:INotification):void
		{
			super.handleNotification_finally(notif);
		}
		
		/**
		 *  @inheritDoc
		 */
		override protected function handleNotification_try(
			notif:INotification):void
		{
			super.handleNotification_try(notif);
		}
		
	} // class
} // package