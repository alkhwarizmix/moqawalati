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
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.LoginBoxMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.MoqawalatiDataListCanvasTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainControlBarTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiHBoxTestCase;

[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class MoqawalatiTestSuite
{
	// controller
	public var moqawalatiBlazeDSGetDataCommandTestCase:MoqawalatiBlazeDSGetDataCommandTestCase;
	
	// model
	public var moqawalatiConfigProxyTestCase:MoqawalatiConfigProxyTestCase;
	
	// view
	public var moqawalatiHBoxTestCase:MoqawalatiHBoxTestCase;
	public var mainControlBarTestCase:MainControlBarTestCase;
	public var moqawalatiDataListCanvasTestCase:MoqawalatiDataListCanvasTestCase;
	public var loginBoxTestCase:LoginBoxTestCase;
	public var loginBoxMediatorTestCase:LoginBoxMediatorTestCase;
	
} // class
} // package