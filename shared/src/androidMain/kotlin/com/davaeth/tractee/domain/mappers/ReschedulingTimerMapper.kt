package com.davaeth.tractee.domain.mappers

import com.davaeth.tractee.domain.Mapper
import com.davaeth.tractee.domain.ReschedulingTimer
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import com.davaeth.tractee.utils.toDuration
import data.TimerEntity
import java.util.Timer

class ReschedulingTimerMapper : Mapper<TimerEntity, ExpectedReschedulingTimer<Timer>> {
    override fun map(input: TimerEntity): ExpectedReschedulingTimer<Timer> =
        ReschedulingTimer(Id(input.id), input.time.toDuration())
}