# Spring Workhsop

This is a simple Spring application to introduce someone to Spring. 
Each commit adds additional features to the project. 


## "initial commit"
- create application with the initializer
    - dependencies: Spring Web
- Resources
  - https://start.spring.io/
  - https://spring.io/guides/gs/testing-web/#scratch

## "create HelloWorldController"
- `DemoApplicationJava`
  - `@SpringBootApplication`
    - short for `@Configuration` & `@EnableAutoConfiguration` & `@EnableWebMvc` & `@ComponentScan`
    - `@ComponentScan` --> look for other components, configurations, and services, looks in the current package, this finds the HelloWorldController
- `HelloWorldController`
  - `@RestController`
    - alternative for `@Controller` & `@ResponseBody`
    - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html
    - uses https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/converter/HttpMessageConverter.html in behind
  - `@GetMapping`
    - short for `@RequestMapping(method=GET)`
- `HelloWorldController`
  - `@SpringBootTest`
    - look for `@SpringBootApplication` and use that to start a Spring application context
  - `@AutoConfigureMockMvc`
    - does not start the server, but will be processed same as if the server was started.
    - alternative inject TestRestTemplate class to make a request to a running server
- Resources
  - https://spring.io/guides/gs/testing-web/

## "use Repository"
- `Movie`
  - `@Entity` -> JPA Annotation to mark as entity
- `MovieRepository`
  - `CrudRepository` -> implements all Crud functions
  - function name is parsed there is also something possible like: findByNameAndReleaseDate(String name, LocalDate releaseDate)
    - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
- `MovieController`
  - similar to `HelloWorldController`
  - can be removed by adding the following to the repository: `@RepositoryRestResource(collectionResourceRel = "people", path = "people")` from org.springframework.boot:spring-boot-starter-data-rest
    - see: https://docs.spring.io/spring-data/rest/docs/current/reference/html/#reference
- `MovieRepositoryTest`
  - `@DataJpaTest` -> creates an inmemory DB that allows us to also test queries
- MovieControllerTest
  - `@MockBean` creates a mock of the repository
  - similar to `HelloWorldController`
- Resources
  - https://spring.io/projects/spring-data#learn
  - https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing
  - https://docs.spring.io/spring-framework/docs/5.3.13/reference/html/testing.html#testing














