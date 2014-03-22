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
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSetCustomizedDataCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiMainFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiLoginUserProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.LoginBoxMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.MoqawalatiDataListCanvasTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainControlBarTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiHBoxTestCase;

[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class MoqawalatiTestSuite
{
	// facade
	public var moqawalatiMainFacadeTestCase:MoqawalatiMainFacadeTestCase;
	
	// controller
	public var moqawalatiBlazeDSGetDataCommandTestCase:MoqawalatiBlazeDSGetDataCommandTestCase;
	public var moqawalatiLoginCommandTestCase:MoqawalatiLoginCommandTestCase;
	public var moqawalatiGetCustomizedDataCommandTestCase:MoqawalatiGetCustomizedDataCommandTestCase;
	public var moqawalatiSetCustomizedDataCommandTestCase:MoqawalatiSetCustomizedDataCommandTestCase;
	public var moqawalatiStartupCommandTestCase:MoqawalatiStartupCommandTestCase;
	
	// model
	public var moqawalatiConfigProxyTestCase:MoqawalatiConfigProxyTestCase;
	public var moqawalatiLoginUserProxyTestCase:MoqawalatiLoginUserProxyTestCase;
	
	// view
	public var moqawalatiHBoxTestCase:MoqawalatiHBoxTestCase;
	public var mainControlBarTestCase:MainControlBarTestCase;
	public var moqawalatiDataListCanvasTestCase:MoqawalatiDataListCanvasTestCase;
	public var loginBoxTestCase:LoginBoxTestCase;
	public var loginBoxMediatorTestCase:LoginBoxMediatorTestCase;
	
} // class
} // package