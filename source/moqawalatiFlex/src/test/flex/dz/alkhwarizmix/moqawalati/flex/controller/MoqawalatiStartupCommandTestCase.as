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

package dz.alkhwarizmix.moqawalati.flex.controller
{

import dz.alkhwarizmix.framework.flex.model.RecordProxy;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiApplication;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiCustomDataProxy;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiLoginUserProxy;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiApplicationMock;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.LoginBoxMediator;
import dz.alkhwarizmix.moqawalati.flex.view.MDICanvasMediator;
import dz.alkhwarizmix.moqawalati.flex.view.MainControlBarMediator;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  Test for MoqawalatiStartupCommand
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢١ جمادى الأول ١٤٣٥ (March 21, 2014)
 */
public class MoqawalatiStartupCommandTestCase extends MoqawalatiPureMVCTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	private var app:IMoqawalatiApplication = null;
	
	override protected function setUp():void
	{
		super.setUp();
		
		app = new MoqawalatiApplicationMock();
		testFacade.registerCommand("NOTE", MoqawalatiStartupCommand);
	}
	
	override protected function tearDown():void
	{
		testFacade.removeCommand("NOTE");
		app = null;
		
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiStartupCommand;
	}
	
	private function get moqawalatiStartupCommand():MoqawalatiStartupCommand
	{
		return classInstanceUnderTest as MoqawalatiStartupCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiStartupCommand);
	}
	
	[Test]
	public function test01_execute_should_register_needed_proxies():void
	{
		testFacade.sendNotification("NOTE", app);
		assertTrue(MoqawalatiConfigProxy.NAME, testFacade.hasProxy(MoqawalatiConfigProxy.NAME));
		assertTrue(MoqawalatiCustomDataProxy.NAME, testFacade.hasProxy(MoqawalatiCustomDataProxy.NAME));
		assertTrue(MoqawalatiLoginUserProxy.NAME, testFacade.hasProxy(MoqawalatiLoginUserProxy.NAME));
		assertTrue(RecordProxy.NAME, testFacade.hasProxy(RecordProxy.NAME));
	}
	
	[Test]
	public function test01_execute_should_register_needed_mediators():void
	{
		testFacade.sendNotification("NOTE", app);
		assertTrue(MainControlBarMediator.NAME, testFacade.hasMediator(MainControlBarMediator.NAME));
		assertTrue(MDICanvasMediator.NAME, testFacade.hasMediator(MDICanvasMediator.NAME));
		assertTrue(LoginBoxMediator.NAME, testFacade.hasMediator(LoginBoxMediator.NAME));
	}
	
} // class
} // package
