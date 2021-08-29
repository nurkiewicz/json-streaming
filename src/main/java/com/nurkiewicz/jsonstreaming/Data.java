package com.nurkiewicz.jsonstreaming;

import java.time.Instant;

class Data {
	private final long seqNo;
	private final Instant timestamp;

	Data(long seqNo, Instant timestamp) {
		this.seqNo = seqNo;
		this.timestamp = timestamp;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public long getSeqNo() {
		return seqNo;
	}
}