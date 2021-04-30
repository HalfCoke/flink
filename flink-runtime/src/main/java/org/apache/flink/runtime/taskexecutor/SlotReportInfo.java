package org.apache.flink.runtime.taskexecutor;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SlotReportInfo {
	private static final String FIELD_NAME_SLOTS_STATUS = "slots-report";

	@JsonProperty(FIELD_NAME_SLOTS_STATUS)
	private final List<SlotStatusInfo> slotStatusInfos;

	@JsonCreator
	public SlotReportInfo(
		@JsonProperty(FIELD_NAME_SLOTS_STATUS) Collection<SlotStatus> slotStatus
	) {
		slotStatusInfos = slotStatus.stream()
			.map(SlotStatusInfo::createSlotStatusInfo)
			.collect(Collectors.toList());
	}

	public List<SlotStatusInfo> getSlotStatusInfos() {
		return slotStatusInfos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SlotReportInfo that = (SlotReportInfo) o;
		return Objects.equals(slotStatusInfos, that.slotStatusInfos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(slotStatusInfos);
	}
}
