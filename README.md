# Creswave Code Test

## 1. Title and Description
**Title**: Blog Application

**Description**: This is a sample Spring Boot project that demonstrates how to build a RESTful API with Spring Boot, JPA, Hibernate, and MySQL.

## 2. Project Overview
This project is a RESTful API that provides basic CRUD (Create, Read, Update, Delete) functionality for a Blog Application.

## 3. Installation Instructions
**Prerequisites**: Java 17+, Maven, and MySQL installed on your machine.

**Steps**:
1. Clone the repository: `git clone https://github.com/Philipbukki/CRESWAVE_CODE_TEST.git`
2. Navigate to the project directory: `cd my-spring-boot-project`
3. Build the project: `mvn clean install`
4. Run the project: `mvn spring-boot:run`

## 4. Usage
Once the server is running, you can interact with the API through http://localhost:8080/swagger-ui/index.html#/
## 5. Documentation
The code for the project is documented using swagger api. To learn more about swagger ui, head to https://swagger.io/tools/swagger-ui/.

## 6. Contributing Guidelines
We welcome contributions! Please see our Contributing Guide for more details.

## 7. License
This project is licensed under the MIT License.

## 8. Contact Information
Maintainer: email: phil.bukki@gmail.com phone: +254-798-335-550.

## 9. Acknowledgments
This project uses the following open-source libraries:
- Spring Boot
- JPA Hibernate
- MySQL

## 10. Detailed Guide
1. **Database Credentials**: Add a database in MySQL db with the name creswave, you can edit db credentials in application.yml file to match the ones in your local configuration
1. **Creating user roles**:
Step is to add user roles, add one with name "ROLE_ADMIN" and another with name "ROLE_USER". N/B role the above two role names are necessary for creating users and the names MUST match. Users are created with default role of "ROLE_USER". To create admin user, create one user with username "admin", this is mandatory as it will help in testing RBAC. After role additions create users using the register endpoint, remember for admin user, username must be "admin" 
1. **Successful Login/Sign In**: After successfully logging in or signing up, you'll see a specific output.
   ![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/877e2a90-03a4-40f3-97cb-f4b92d1680f0)
3. **Authentication Token**: Copy the generated authentication token and use it as shown using the Authorize button. This will enable you to access all the endpoints without having to sign in each time.
4. **Creating a Blog Post**: Only authenticated users are allowed to add blog posts. There's a specific process for creating a new blog post.
5. **Successful Post Request**: The expected output with a successful post request for the blog post is shown.
   ![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/668a4c50-0b43-4a7c-9480-52c3496f0f43)
7. **Retrieving All Blog Posts**: To retrieve all blog posts, use the 'get all blog posts' endpoint. Provide the required parameters for retrieving the posts as the posts are paged. Default values are provided.
8. **Searching Blog Posts**: To search blog posts by title or content, enter the blog post title or content. The result is a list of posts having content or title the same as the one passed in the query parameter.
9. **Deleting and Updating Blog Posts**: For deleting and updating blog posts, one can only perform these actions if they created the post or have "ROLE_ADMIN" authority.
10. **Adding a Comment to a Blog Post**: To add a comment to a blog post, specify the postId as a path variable to proceed.
11. **Successful Comment Posting**: The image shows the response after successfully posting a comment to a post.
    ![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/cab64be4-d201-4101-a412-15dcd40fd466)
13. **Retrieving All Comments Belonging to a Post**: To retrieve all comments belonging to a post, provide the necessary request parameters. Default values have been provided.
14. **Deleting and Updating Blog Post Comment**: To delete and update a blog post comment, provide the post id and comment id for the comment you want to delete. Only the comment poster or user with role "ROLE_ADMIN" can do this.

