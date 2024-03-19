##### Access the application using the link: http://localhost:8080/swagger-ui/index.html#/
##### First step is to add user roles, add one with name "ROLE_ADMIN" and another with name "ROLE_USER"
#### N/B role the above two role names are necessary for creating users and the names MUST match
##### Users are created with default role of "ROLE_USER"
##### To create admin user, create one user with username "admin", this is mandatory as it will help in testing RBAC
##### After role additions create users using the register endpoint, remember for admin user, username must be "admin" 
##### Login to the application using the above created user details using login/signup endpoints.
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/d94db8d1-23be-43ee-8286-022c5c732c31)
##### Expected output incase of successful login/SignIn is as shown below.
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/877e2a90-03a4-40f3-97cb-f4b92d1680f0)
##### Copy the generated authentication token, and use it as shown below using the Authorize button.
##### This will enable you access all the endpoints without having to sign in each and every time.
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/0980994c-f3ce-4f58-a0c2-c2072d44a3ea)

##### Below image Illustrates how to create Create a new Blog Post
#### N/B only authenticated users are allowed to add blog posts
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/42e773e6-d5b7-4a74-b967-ea236fec49b8)
##### Expected ouput with successful post request for the blog post is as shown below
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/5efa198b-354f-484f-b20e-36b8396c6b30)

##### To retrieve all blog posts use the get all blog posts as shown below.
##### N/B provide the params requied for retrieving the posts as the posts are paged. Default values are provided for the same
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/d756396b-aee9-4750-938a-043c5e6327aa)
##### Below shows the list of retrieved posts paged data
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/6983f67f-2a12-45e9-9033-939d3fea7887)
##### For deleting and updating of blog posts, one can only perform these actions if they created the post or have "ROLE_ADMIN" authority.
##### Below shows response for users with no "ROLE_ADMIN" trying to delete blog posts they didn't create
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/9eab2223-d91e-4abb-923c-4d0f593a96b7)
##### To add comment to a blog post, specify postId as a path varible to be able to proceed, below image illustrates the same
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/14266fb1-104b-4795-9030-e02690b62d76)
##### The image below shows response after successfully posting a comment to a post
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/cab64be4-d201-4101-a412-15dcd40fd466)
##### To retrieve all comments belonging to a post, provide the necessary request parameters, default values have been provided
##### Below is an example reponse for such request
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/172773bb-c956-4d42-b835-5a8c25baad33)
##### To delete and update blog post comment, provide the post id and comment id for the comment you want to delete
##### N/B Only the comment poster or user with role "ROLE_ADMIN" can perform the above actions
##### Below is the endpoint for updating user profile details, have restricted this to only name and user password
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/bc6d99dc-8fd8-41ea-a1e1-fa7a2388daae)
##### To run the project, clone this project from this repository and edit the application.yml file for the db name and db credentials
##### In case of any queries or challenges while running the project, please do not hesitate to contact me via email at phil.bukki@gmail.com or by phone at +254798335550.


