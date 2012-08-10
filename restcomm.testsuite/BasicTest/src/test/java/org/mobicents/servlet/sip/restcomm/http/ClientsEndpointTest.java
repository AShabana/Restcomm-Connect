/*
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.mobicents.servlet.sip.restcomm.http;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mobicents.servlet.sip.restcomm.entities.Client;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.instance.Account;

/**
 * @author quintana.thomas@gmail.com (Thomas Quintana)
 */
// @RunWith(Arquillian.class)
public class ClientsEndpointTest extends AbstractEndpointTest {
	
	final String endpoint = "http://127.0.0.1:8888/restcomm";
	
	public ClientsEndpointTest() {
		super();
	}

	@Test public void createClientTest() throws TwilioRestException {
		final TwilioRestClient twilioClient = new TwilioRestClient("ACae6e420f425248d6a26948c17a9e2acf",
				"77f8c12cc7b8f8423e5c38b035249166", endpoint);
		assertTrue(twilioClient.getEndpoint().equalsIgnoreCase("http://127.0.0.1:8888/restcomm"));
		final Account account = twilioClient.getAccount();
		final Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("friendlyName", "testClientEndpoint");
		parameters.put("login", "test");
		parameters.put("password", "1234");
		parameters.put("VoiceUrl", endpoint+"/demo/hello-world.xml");
		parameters.put("VoiceMethod", "POST");
		parameters.put("VoiceFallbackUrl", endpoint+"/demo/hello-world.xml");
		parameters.put("VoiceFallbackMethod", "POST");
		TwilioRestResponse response = twilioClient.safeRequest(endpoint+"/2012-04-24/Accounts/ACae6e420f425248d6a26948c17a9e2acf/Clients.json", "POST", parameters);
		
		assertTrue(response.getHttpStatus()==200);
		
	}
}
