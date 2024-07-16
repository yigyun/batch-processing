## Spring Batch 공부
- JVM 환경에서 de facto 표준에 해당하고, 현재 본인이 Spring 프레임워크를 사용하고 있기 때문에, 배치 처리를 통해 일괄적인 처리를 사용해보기 위함.
- 스프링 배치는 스프링 API와 통합하여 사용하기 편하다.
- 관계형 DB, NoSQL에 대한 지원, 메시징 지원을 통해 사용 사례를 처리한다.
![image](https://github.com/user-attachments/assets/1fa84b35-7fd3-4255-a5d9-e254a41a58db)
- ItemReader, ItemWriter, ItemProcessor를 통해서 record 객체를 인스턴스화 하여 데이터를 다룰 수 있다.
- Job에 step을 거치면서 해당 일을 처리한다. 여러 단계도 구현 가능하다.
