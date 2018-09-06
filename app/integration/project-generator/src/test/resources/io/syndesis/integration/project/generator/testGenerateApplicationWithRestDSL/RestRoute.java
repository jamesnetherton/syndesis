package io.syndesis.example;

import javax.annotation.Generated;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.CollectionFormat;
import org.apache.camel.model.rest.RestParamType;

/**
 * Generated from Swagger specification by Camel REST DSL generator.
 */
@Generated("org.apache.camel.generator.swagger.AppendableGenerator")
public final class RestRoute extends RouteBuilder {
    /**
     * Defines Apache Camel routes using REST DSL fluent API.
     */
    public void configure() {
        rest()
            .put("/pet")
                .id("updatePet")
                .consumes("application/json,application/xml")
                .produces("application/xml,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("Pet object that needs to be added to the store")
                .endParam()
                .to("direct:updatePet")
            .post("/pet")
                .id("addPet")
                .consumes("application/json,application/xml")
                .produces("application/xml,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("Pet object that needs to be added to the store")
                .endParam()
                .to("direct:addPet")
            .get("/pet/findByStatus")
                .id("findPetsByStatus")
                .description("Multiple status values can be provided with comma separated strings")
                .produces("application/xml,application/json")
                .param()
                    .name("status")
                    .type(RestParamType.query)
                    .dataType("array")
                    .collectionFormat(CollectionFormat.multi)
                    .arrayType("string")
                    .required(true)
                    .description("Status values that need to be considered for filter")
                .endParam()
                .to("direct:findPetsByStatus")
            .get("/pet/findByTags")
                .id("findPetsByTags")
                .description("Muliple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.")
                .produces("application/xml,application/json")
                .param()
                    .name("tags")
                    .type(RestParamType.query)
                    .dataType("array")
                    .collectionFormat(CollectionFormat.multi)
                    .arrayType("string")
                    .required(true)
                    .description("Tags to filter by")
                .endParam()
                .to("direct:findPetsByTags")
            .get("/pet/{petId}")
                .id("getPetById")
                .description("Returns a single pet")
                .produces("application/xml,application/json")
                .param()
                    .name("petId")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of pet to return")
                .endParam()
                .to("direct:getPetById")
            .post("/pet/{petId}")
                .id("updatePetWithForm")
                .consumes("application/x-www-form-urlencoded")
                .produces("application/xml,application/json")
                .param()
                    .name("petId")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of pet that needs to be updated")
                .endParam()
                .param()
                    .name("name")
                    .type(RestParamType.formData)
                    .dataType("string")
                    .required(false)
                    .description("Updated name of the pet")
                .endParam()
                .param()
                    .name("status")
                    .type(RestParamType.formData)
                    .dataType("string")
                    .required(false)
                    .description("Updated status of the pet")
                .endParam()
                .to("direct:updatePetWithForm")
            .delete("/pet/{petId}")
                .id("deletePet")
                .produces("application/xml,application/json")
                .param()
                    .name("api_key")
                    .type(RestParamType.header)
                    .dataType("string")
                    .required(false)
                .endParam()
                .param()
                    .name("petId")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("Pet id to delete")
                .endParam()
                .to("direct:deletePet")
            .post("/pet/{petId}/uploadImage")
                .id("uploadFile")
                .consumes("multipart/form-data")
                .produces("application/json")
                .param()
                    .name("petId")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of pet to update")
                .endParam()
                .param()
                    .name("additionalMetadata")
                    .type(RestParamType.formData)
                    .dataType("string")
                    .required(false)
                    .description("Additional data to pass to server")
                .endParam()
                .param()
                    .name("file")
                    .type(RestParamType.formData)
                    .dataType("file")
                    .required(false)
                    .description("file to upload")
                .endParam()
                .to("direct:uploadFile")
            .get("/store/inventory")
                .id("getInventory")
                .description("Returns a map of status codes to quantities")
                .produces("application/json")
                .to("direct:getInventory")
            .post("/store/order")
                .id("placeOrder")
                .produces("application/xml,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("order placed for purchasing the pet")
                .endParam()
                .to("direct:placeOrder")
            .get("/store/order/{orderId}")
                .id("getOrderById")
                .description("For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions")
                .produces("application/xml,application/json")
                .param()
                    .name("orderId")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of pet that needs to be fetched")
                .endParam()
                .to("direct:getOrderById")
            .delete("/store/order/{orderId}")
                .id("deleteOrder")
                .description("For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors")
                .produces("application/xml,application/json")
                .param()
                    .name("orderId")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of the order that needs to be deleted")
                .endParam()
                .to("direct:deleteOrder")
            .post("/user")
                .id("createUser")
                .description("This can only be done by the logged in user.")
                .produces("application/xml,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("Created user object")
                .endParam()
                .to("direct:createUser")
            .post("/user/createWithArray")
                .id("createUsersWithArrayInput")
                .produces("application/xml,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("List of user object")
                .endParam()
                .to("direct:createUsersWithArrayInput")
            .post("/user/createWithList")
                .id("createUsersWithListInput")
                .produces("application/xml,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("List of user object")
                .endParam()
                .to("direct:createUsersWithListInput")
            .get("/user/login")
                .id("loginUser")
                .produces("application/xml,application/json")
                .param()
                    .name("username")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .description("The user name for login")
                .endParam()
                .param()
                    .name("password")
                    .type(RestParamType.query)
                    .dataType("string")
                    .required(true)
                    .description("The password for login in clear text")
                .endParam()
                .to("direct:loginUser")
            .get("/user/logout")
                .id("logoutUser")
                .produces("application/xml,application/json")
                .to("direct:logoutUser")
            .get("/user/{username}")
                .id("getUserByName")
                .produces("application/xml,application/json")
                .param()
                    .name("username")
                    .type(RestParamType.path)
                    .dataType("string")
                    .required(true)
                    .description("The name that needs to be fetched. Use user1 for testing. ")
                .endParam()
                .to("direct:getUserByName")
            .put("/user/{username}")
                .id("updateUser")
                .description("This can only be done by the logged in user.")
                .produces("application/xml,application/json")
                .param()
                    .name("username")
                    .type(RestParamType.path)
                    .dataType("string")
                    .required(true)
                    .description("name that need to be updated")
                .endParam()
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("Updated user object")
                .endParam()
                .to("direct:updateUser")
            .delete("/user/{username}")
                .id("deleteUser")
                .description("This can only be done by the logged in user.")
                .produces("application/xml,application/json")
                .param()
                    .name("username")
                    .type(RestParamType.path)
                    .dataType("string")
                    .required(true)
                    .description("The name that needs to be deleted")
                .endParam()
                .to("direct:deleteUser");
    }
}