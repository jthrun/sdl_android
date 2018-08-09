package com.smartdevicelink.protocol;

import android.os.Bundle;

import com.smartdevicelink.protocol.enums.*;
import com.smartdevicelink.transport.enums.TransportType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProtocolListener {
	// Called to indicate that these bytes are to be sent as part of a message.
	// This call includes the part of the message.
	void onProtocolMessageBytesToSend(SdlPacket packet);

	// Called to indicate that a complete message (RPC, BULK, etc.) has been
	// received.  This call includes the message.
	void onProtocolMessageReceived(ProtocolMessage msg);

	// Called to indicate that a protocol session has been started (from either side)
	void onProtocolSessionStarted(SessionType sessionType, byte sessionID, byte version, String correlationID, int hashID, boolean isEncrypted);
	
	void onProtocolSessionNACKed(SessionType sessionType, byte sessionID, byte version,
	                             String correlationID, List<String> rejectedParams);

	// Called to indicate that a protocol session has ended (from either side)
	void onProtocolSessionEnded(SessionType sessionType, byte sessionID, String correlationID /*, String info, Exception ex*/);
 	
	void onProtocolSessionEndedNACKed(SessionType sessionType, byte sessionID, String correlationID /*, String info, Exception ex*/);

	void onProtocolHeartbeat(SessionType sessionType, byte sessionID);
	
	/**
     * Called when a protocol heartbeat ACK message has been received from SDL.
     */
    void onProtocolHeartbeatACK(SessionType sessionType, byte sessionID);
    
    void onProtocolServiceDataACK(SessionType sessionType, int dataSize, byte sessionID);

    void onResetOutgoingHeartbeat(SessionType sessionType, byte sessionID);

    void onResetIncomingHeartbeat(SessionType sessionType, byte sessionID);

	// Called to indicate that a protocol error was detected in received data.
	void onProtocolError(String info, Exception e);

	// Called when SDLCore has updated secondary transport info or secondary transport is unavailable
	void connectSecondaryTransport(byte sessionID, TransportType transportType, Bundle params);
} // end-interfCe
