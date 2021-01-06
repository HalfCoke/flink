package org.apache.flink.runtime.rest.messages.job;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SubtaskInfo {

	private static final String FIELD_NAME_SUB_ID = "sub-id";

	private static final String FIELD_NAME_SLOT_ID = "slot-id";

	@JsonProperty(FIELD_NAME_SUB_ID)
	private final String subID;

	@JsonProperty(FIELD_NAME_SLOT_ID)
	private final String slotID;


	@JsonCreator
	public SubtaskInfo(
		@JsonProperty(FIELD_NAME_SUB_ID) String subID,
		@JsonProperty(FIELD_NAME_SLOT_ID) String slotID
	) {
		this.subID = subID;
		this.slotID = slotID;
	}


	public String getSubID() {
		return subID;
	}

	public String getSlotID() {
		return slotID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SubtaskInfo that = (SubtaskInfo) o;
		return Objects.equals(subID, that.subID) &&
			Objects.equals(slotID, that.slotID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subID, slotID);
	}
}
