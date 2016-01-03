////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٣ ربيع الاول ١٤٣٧ (January 02, 2016)
 */
public class WinrakPositionWorker {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	public WinrakPositionWorker(final IWinrakService winrakService) {
		this.winrakService = winrakService;
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	private final Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(WinrakPositionWorker.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private final IWinrakService winrakService;
	private List<Thread> threadListForConvertPositionToAddress;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 *
	 * @param position
	 * @throws AlKhwarizmixException
	 */
	public void fillPositionAddress(final IWinrakPosition position,
			final long timeout_ms) throws AlKhwarizmixException {
		final Thread t = new Thread(new WinrakPositionThread(position,
				timeout_ms, winrakService));
		getThreadListForConvertPositionToAddress().add(t);
		t.start();
	}

	/**
	 *
	 * @throws InterruptedException
	 */
	public void waitForAllFillPositionAddress(final long millis) {
		for (final Thread t : getThreadListForConvertPositionToAddress())
			try {
				t.join(millis);
			} catch (final InterruptedException e) {
				getLogger().warn("waitForAllFillPositionAddress: {}",
						e.getStackTrace());
			}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// threadListForConvertPositionToAddress
	// ----------------------------------

	private List<Thread> getThreadListForConvertPositionToAddress() {
		if (threadListForConvertPositionToAddress == null)
			threadListForConvertPositionToAddress = new ArrayList<Thread>();
		return threadListForConvertPositionToAddress;
	}

	// --------------------------------------------------------------------------
	//
	// Internal class
	//
	// --------------------------------------------------------------------------

	final class WinrakPositionThread implements Runnable {
		private final IWinrakPosition pos;
		private final long timeout_ms;
		private final IWinrakService winrakService;

		public WinrakPositionThread(final IWinrakPosition pos,
				final long timeout_ms, final IWinrakService winrakService) {
			this.pos = pos;
			this.timeout_ms = timeout_ms;
			this.winrakService = winrakService;
		}

		@Override
		public void run() {
			try {
				pos.setAddress(winrakService.convertPositionToAddress(
						pos.getLat(), pos.getLon(), timeout_ms));
			} catch (final AlKhwarizmixException e) {
				getLogger().warn("WinrakPositionThread run: {}",
						e.getStackTrace());
			}
		}
	}

} // Class
