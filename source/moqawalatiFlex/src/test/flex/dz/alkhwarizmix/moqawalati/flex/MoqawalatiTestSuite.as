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

package dz.alkhwarizmix.moqawalati.flex
{

import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiBlazeDSGetDataCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiGetCustomizedDataCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLoginCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLogoutCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSetCustomizedDataCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiWebGetDataCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.event.MoqawalatiEventTestCase;
import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiMainFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.messaging.MoqawalatiConsumerTestCase;
import dz.alkhwarizmix.moqawalati.flex.messaging.MoqawalatiProducerTestCase;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiLoginUserProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.resources.MoqawalatiResourceManagerTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.LoginBoxMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.MDICanvasMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.MainControlBarMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.MoqawalatiDataListCanvasTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxEventTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainCanvasTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainControlBarEventTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainControlBarTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiCanvasTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiHBoxTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiMDICanvasTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiMDIWindowTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.TestAndDebugWindowTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.controls.MoqawalatiButtonTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.controls.MoqawalatiCheckBoxTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.controls.MoqawalatiLabelTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.controls.MoqawalatiMenuBarTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.controls.MoqawalatiTextInputTestCase;

[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class MoqawalatiTestSuite
{
	// facade
	public var moqawalatiMainFacadeTestCase:MoqawalatiMainFacadeTestCase;
	public var moqawalatiFacadeTestCase:MoqawalatiFacadeTestCase;
	
	// messaging
	public var moqawalatiConsumerTestCase:MoqawalatiConsumerTestCase;
	public var moqawalatiProducerTestCase:MoqawalatiProducerTestCase;
	
	// controller
	public var moqawalatiBlazeDSGetDataCommandTestCase:MoqawalatiBlazeDSGetDataCommandTestCase;
	public var moqawalatiGetCustomizedDataCommandTestCase:MoqawalatiGetCustomizedDataCommandTestCase;
	public var moqawalatiSetCustomizedDataCommandTestCase:MoqawalatiSetCustomizedDataCommandTestCase;
	public var moqawalatiStartupCommandTestCase:MoqawalatiStartupCommandTestCase;
	public var moqawalatiLoginCommandTestCase:MoqawalatiLoginCommandTestCase;
	public var moqawalatiLogoutCommandTestCase:MoqawalatiLogoutCommandTestCase;
	public var moqawalatiWebGetDataCommandTestCase:MoqawalatiWebGetDataCommandTestCase;
	
	// event
	public var moqawalatiEventTestCase:MoqawalatiEventTestCase;
	
	// model
	public var moqawalatiConfigProxyTestCase:MoqawalatiConfigProxyTestCase;
	public var moqawalatiLoginUserProxyTestCase:MoqawalatiLoginUserProxyTestCase;
	public var moqawalatiMDICanvasTestCase:MoqawalatiMDICanvasTestCase;
	public var moqawalatiProxyTestCase:MoqawalatiProxyTestCase;
	
	// resources
	public var moqawalatiResourceManagerTestCase:MoqawalatiResourceManagerTestCase;
	
	// view
	public var moqawalatiHBoxTestCase:MoqawalatiHBoxTestCase;
	public var mainControlBarTestCase:MainControlBarTestCase;
	public var moqawalatiDataListCanvasTestCase:MoqawalatiDataListCanvasTestCase;
	public var loginBoxTestCase:LoginBoxTestCase;
	public var loginBoxMediatorTestCase:LoginBoxMediatorTestCase;
	public var moqawalatiButtonTestCase:MoqawalatiButtonTestCase;
	public var moqawalatiTextInputTestCase:MoqawalatiTextInputTestCase;
	public var moqawalatiCheckBoxTestCase:MoqawalatiCheckBoxTestCase;
	public var moqawalatiLabelTestCase:MoqawalatiLabelTestCase;
	public var moqawalatiMenuBarTestCase:MoqawalatiMenuBarTestCase;
	public var mdiCanvasMediatorTestCase:MDICanvasMediatorTestCase;
	public var mainControlBarMediatorTestCase:MainControlBarMediatorTestCase;
	
	// view.components
	public var loginBoxEventTestCase:LoginBoxEventTestCase;
	
	// view.containers
	public var testAndDebugWindowTestCase:TestAndDebugWindowTestCase;
	public var mainCanvasTestCase:MainCanvasTestCase;
	public var mainControlBarEventTestCase:MainControlBarEventTestCase;
	public var moqawalatiMDIWindowTestCase:MoqawalatiMDIWindowTestCase;
	public var moqawalatiCanvasTestCase:MoqawalatiCanvasTestCase;
	
} // class
} // package
