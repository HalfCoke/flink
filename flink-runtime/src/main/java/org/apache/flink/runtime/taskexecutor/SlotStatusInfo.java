package org.apache.flink.runtime.taskexecutor;


import org.apache.flink.runtime.rest.messages.ResourceProfileInfo;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SlotStatusInfo {
	private static final String FIELD_NAME_SLOT_ID = "slot-id";

	private static final String FILED_NAME_ALLOCATION_ID = "allocation-id";

	private static final String FILED_NAME_JOB_ID = "job-id";

	private static final String FILED_NAME_RESOURCE_PROFILE_INFO = "resource-profile";

	private static final String FILED_NAME_MANAGED_MEM_USED = "managed-used";

	@JsonProperty(FIELD_NAME_SLOT_ID)
	private final String slotID;

	@JsonProperty(FILED_NAME_ALLOCATION_ID)
	private final String allocationID;

	@JsonProperty(FILED_NAME_JOB_ID)
	private final String jobID;

	@JsonProperty(FILED_NAME_RESOURCE_PROFILE_INFO)
	private final ResourceProfileInfo resourceProfileInfo;

	@JsonProperty(FILED_NAME_MANAGED_MEM_USED)
	private final String managedMemUsed;

	@JsonCreator
	public SlotStatusInfo(
		@JsonProperty(FIELD_NAME_SLOT_ID) String slotID,
		@JsonProperty(FILED_NAME_ALLOCATION_ID) String allocationID,
		@JsonProperty(FILED_NAME_JOB_ID) String jobID,
		@JsonProperty(FILED_NAME_RESOURCE_PROFILE_INFO) ResourceProfileInfo resourceProfileInfo,
		@JsonProperty(FILED_NAME_MANAGED_MEM_USED) String managedMemUsed) {
		this.slotID = slotID;
		this.allocationID = allocationID;
		this.jobID = jobID;
		this.resourceProfileInfo = resourceProfileInfo;
		this.managedMemUsed = managedMemUsed;
	}

	private SlotStatusInfo(SlotStatus slotStatus) {
		this(
			slotStatus.getSlotID().toString(),
			slotStatus.getAllocationID() == null ? "" : slotStatus.getAllocationID().toHexString(),
			slotStatus.getJobID() == null ? "": slotStatus.getJobID().toHexString(),
			ResourceProfileInfo.fromResrouceProfile(slotStatus.getResourceProfile()),
			slotStatus.getManagedUsed()
		);
	}

	public static SlotStatusInfo createSlotStatusInfo(SlotStatus slotStatus) {
		return new SlotStatusInfo(slotStatus);
	}

	public String getSlotID() {
		return slotID;
	}

	public String getAllocationID() {
		return allocationID;
	}

	public String getJobID() {
		return jobID;
	}

	public ResourceProfileInfo getResourceProfileInfo() {
		return resourceProfileInfo;
	}

	public String getManagedMemUsed() {
		return managedMemUsed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SlotStatusInfo that = (SlotStatusInfo) o;
		return Objects.equals(slotID, that.slotID) &&
			Objects.equals(allocationID, that.allocationID) &&
			Objects.equals(jobID, that.jobID) &&
			Objects.equals(resourceProfileInfo, that.resourceProfileInfo) &&
			Objects.equals(managedMemUsed, that.managedMemUsed);
	}

	@Override
	public int hashCode() {
		return Objects.hash(slotID, allocationID, jobID, resourceProfileInfo, managedMemUsed);
	}
}
