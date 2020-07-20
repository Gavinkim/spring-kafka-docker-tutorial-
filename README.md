# spring boot kafka demo with docker

## ref
* [kafka with springboot](https://medium.com/@jjeaby/kafka-%EB%A5%BC-%EB%A7%8E%EC%9D%B4-%EC%82%AC%EC%9A%A9%ED%95%9C%EB%8B%A4%EA%B3%A0%EB%8A%94-%EC%95%8C%EA%B3%A0-%EC%9E%88%EC%97%88%EC%A7%80%EB%A7%8C-%EC%9D%BC%EC%9D%84-%ED%95%98%EB%A9%B4%EC%84%9C-%EC%82%AC%EC%9A%A9%ED%95%A0-%EA%B8%B0%ED%9A%8C%EA%B0%80-%EC%97%86%EC%96%B4%EC%84%9C-%ED%95%91%EA%B3%84-%EC%B0%BE%EC%95%84-fa89ff6c498f)

## description
* [kafka ref](https://epicdevs.com/17)
<pre>
kafka 는 pub-sub 모델을 기반으로 동작한다.
구성요소:
    zookeeper:
        producer 와 consumer 를 관리한다.
    producer:
        topic 의 메시지 생성후 해당 메시지를 broker 에 전달
    consumer:
        topic 을 subscribe 한다.
        메시지를 pull 방식으로 broker 로부터 가져오기 때문에 batch consumer 구현 가능.
    broker:
        topic 을 기준으로 메시지 관리
        전달받은 메시지를 topic 별로 분류하여 적재
    topic:
        partition 단위로 구성 된다.
        클러스터 서버에 분산 저장할 경우 partition 단위로 각 서버들에 분산되어 복제 된다.
        topic 내에서 메시지를 식별 할 때는 partition 번호와 offset 값을 함께 사용
    consumer group:
        partition 은 consumer group 마다 오직 하나의 consumer 의 접근만을 허용하며, 해당 consumer 를 partition owner 라고 한다.
        즉 동일한 consumer group 에 속하는 consumer 들과 동일한 partition 에 접근 할 수 없다.
    partition:
        0 부터 1씩 증가하는 offset 값을 메시지에 부여한다.
    offset:
        각 partition 내에서 메시지를 식별하는 id 로 사용됨.
        offset 값은 partition 마다 별도로 관리된다.
       
</pre>
### docker-compose description
* Schema Registry
```
RESTful 인터페이스를 사용하여 스키마(Schema)를 관리하거나 조회하는 기능을 제공합니다.
```
* Schema Registry UI
```
schema-registry-ui는 스키마 레지스트리의 RESTful 기능을 UI 로 제공해주는 서비스입니다. Schema 조회, 생성, 수정, 삭제를 UI 로 제공합니다.
```
* Kafka Rest Proxy
```
Kafka 클러스터에 RESTful 인터페이스를 제공합니다. API를 사용하여 메시지를 생성해서 이용할 수 있습니다.
```
* Kafka Topics UI
```
Kafka Topic을 조회하고 Kafka Cluster 의 상태를 보여주는 UI 서비스로 Kafka Rest Proxy 를 위한 서비스입니다.
```
* Ksqldb Server
```
KSQL은 Kafka 클러스터와 연동 되며 Topic 에 대해 query 문을 작성해주는 서비스로 Schema Registry UI 와 연동 된다.
```

### 실행 및 확인
* 메시지 publish
> localhost:9991/send/music/dance-monkey
> localhost:9991/send/news/covid19
* topic 확인
> localhost:8000
