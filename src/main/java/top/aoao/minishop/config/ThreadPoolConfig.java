package top.aoao.minishop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;

@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);  // 核心线程数
        executor.setMaxPoolSize(10);  // 最大线程数
        executor.setQueueCapacity(10); // 队列容量
        executor.setThreadNamePrefix("Worker-");
        // 2. 拒绝策略（队列满+线程达最大时，记录日志并丢弃任务）
        RejectedExecutionHandler rejectedHandler = (r, exec) -> {
            log.error("线程池饱和，任务被拒绝！当前活跃线程数：{}，队列剩余容量：{}",
                    exec.getActiveCount(),
                    exec.getQueue().remainingCapacity());
        };
        executor.setRejectedExecutionHandler(rejectedHandler);
        executor.initialize();
        return executor;
    }
}
