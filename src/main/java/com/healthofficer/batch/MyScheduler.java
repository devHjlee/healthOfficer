package com.healthofficer.batch;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT1M")
@Component
public class MyScheduler {
    @Scheduled(cron = "0/10 * * * * *") // 0초부터 10초마다 실행
    @SchedulerLock(
            name = "scheduler_lock", // 스케줄러 락 이름 지정. (이름이 동일한 스케줄러일 경우, 락의 대상이 된다.)
            lockAtLeastFor = "PT9S", // 락을 유지하는 시간을 설정한다. (9초로 설정했는데, 스케줄러 주기보다 약간 짧게 지정하는 것이 좋다.)
            lockAtMostFor = "PT9S" // 보통 스케줄러가 잘 동작하여 잘 종료된 경우 잠금을 바로 해제하게 되는데, 스케줄러 오류가 발생하면 잠금이 해제되지 않는다. 이런 경우 잠금을 유지하는 시간을 설정한다.
    )
    public void scheduler1() {
        log.info("8080 1번 스케줄러");
    }

    @Scheduled(cron = "0/10 * * * * *")
    @SchedulerLock(
            name = "scheduler_lock2",
            lockAtLeastFor = "PT9S",
            lockAtMostFor = "PT9S"
    )
    public void scheduler2() {
        log.info("8080 2번 스케줄러");
    }
}