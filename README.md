## Spring Batch 공부

-------------

- JVM 환경에서 de facto 표준에 해당하고, 현재 본인이 Spring 프레임워크를 사용하고 있기 때문에, 배치 처리를 통해 일괄적인 처리를 사용해보기 위함.
- 스프링 배치는 스프링 API와 통합하여 사용하기 편하다.
- 관계형 DB, NoSQL에 대한 지원, 메시징 지원을 통해 사용 사례를 처리한다.
- ItemReader, ItemWriter, ItemProcessor를 통해서 record 객체를 인스턴스화 하여 데이터를 다룰 수 있다.
- Job에 step을 거치면서 해당 일을 처리한다. 여러 단계도 구현 가능하다.
- 보통 다른 스케줄러를 통해 특정 시간 또는 주기에 따라 배치 작업을 실행 한다.

### 1. 스프링 배치란

- 스프링 배치는 대용량 데이터 처리를 위한 프레임워크
- 대용량 데이터를 처리하는 경우나 주기적인 업무 일괄 처리 등에 사용
- 스프링 배치는 배치 작업을 트랜잭션 처리와 함께 관리하고, 실패한 작업을 복구하고 재시작할 수 있는 기능을 제공
- Job, Step, ItemReader, ItemProcessor, ItemWriter, JobRepository, JobLauncher 등의 개념을 제공

### 2. 스프링 배치 아키텍처

![image](https://github.com/user-attachments/assets/1fa84b35-7fd3-4255-a5d9-e254a41a58db)

#### Job
- 배치 처리의 실행 단위로 Step 인스턴스를 담는 컨테이너 역할을 한다.
- Job은 하나 이상의 Step을 가지며, 각 Step은 정확히 하나의 ItemReader, ItemProcessor, ItemWriter를 가질 수 있다.
- Job은 Job Launcher를 통해 실행되어야 하며, 현재 실행 중인 프로세스에 대한 메타 데이터는 Job Repository에 저장되어야 한다.

#### Step

- Job을 구성하는 단위 작업으로, ItemReader, ItemProcessor, ItemWriter를 포함하고 데이터 처리 과정을 정의한다.

#### JobLauncher

- Job을 실행하는 역할을 한다. JobParameters를 전달하여 특정 JobInstance를 실행한다.

#### ItemReader

- 데이터 소스에서 데이터를 읽어오는 역할을 한다. 데이터베이스, 파일 등 다양한 소스에서 데이터를 읽을 수 있다.
- JdbcCursorItemReaderBuilder<객체>를 통해 DB에서 읽기, 파일에서 FlatFileItemReaderBuilder로 읽기 가능하다.

#### ItemProcessor

- ItemReader에서 읽어온 데이터를 가공하거나 필터링, 검증하는 역할을 한다.
    ```
    @Bean
    public ItemProcessor<Order, Order> orderItemProcessor() {
    return order -> {
    order.setStatus("배송 중");
    return order;
    };}
  ```
  
#### ItemWriter

- ItemProcessor에서 처리한 데이터를 저장하는 역할을 담당한다.
    ```
    @Bean
    public JdbcBatchItemWriter<Order> orderItemWriter(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Order>()
    .dataSource(dataSource)
    .sql("INSERT INTO shipping (order_id, status) VALUES (:id, :status)")
    .beanMapped()
    .build();
    }
  ```

#### JobRepository

- Job 실행 정보를 저장하는 저장소로 JobInstance, JobExecution, StepExecution 등의 메타데이터를 관리합니다.

