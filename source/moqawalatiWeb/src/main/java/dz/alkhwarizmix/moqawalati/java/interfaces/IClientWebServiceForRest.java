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

package dz.alkhwarizmix.moqawalati.java.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (February 04, 2014) TODO: HIDJRI
 */
public interface IClientWebServiceForRest {

	/**
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addClient(
			@RequestParam("client") String client) throws MoqawalatiException;

	/**
	 */
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	public ResponseEntity<String> getClientById(
			@PathVariable("clientId") String clientId)
			throws MoqawalatiException;

	/**
	 */
	@RequestMapping(value = "/{clientId}", method = RequestMethod.POST)
	public ResponseEntity<String> updateClient(
			@PathVariable("clientId") String clientId,
			@RequestParam("client") String client) throws MoqawalatiException;

	/**
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getClientList(
			@RequestParam("firstResult") int firstResult,
			@RequestParam("maxResult") int maxResult);

} // Class
