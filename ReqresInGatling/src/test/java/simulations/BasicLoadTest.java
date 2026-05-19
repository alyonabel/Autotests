package simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicLoadTest extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://reqres.in")
            .acceptHeader("application/json");

    ScenarioBuilder users = scenario("Load Test").exec(http("Get Users").get("/api/users?page=1").check(status().is(200)));
    ScenarioBuilder singleUser = scenario("Get Single User").exec(http("User").get("/api/users/2"));
    {
       setUp(
                users.injectOpen(rampUsers(20).during(10)),
                singleUser.injectOpen(rampUsers(20).during(10))

       ).protocols(httpProtocol);
    }


}
