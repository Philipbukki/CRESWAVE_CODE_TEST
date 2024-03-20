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
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/07229024-039a-4303-a39c-7c79436968c1)
##### Expected ouput with successful post request for the blog post is as shown below
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/668a4c50-0b43-4a7c-9480-52c3496f0f43)
##### To retrieve all blog posts use the get all blog posts as shown below.
##### N/B provide the params requied for retrieving the posts as the posts are paged. Default values are provided for the same
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/d756396b-aee9-4750-938a-043c5e6327aa)
##### Below shows the list of retrieved posts paged data
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/d0b5b41c-e838-4cbf-82af-aa8d7fa30392)
##### To search blog posts by title or content, enter the blog post title or content as shown below, result is
##### a list of posts having content or title same as the one passed in the query param 
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/3ecee9ac-fcb1-4b4d-a4c4-b55ffaf44f21)
##### Below is an example response for posts with title "Effective Java"
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/a96da75d-94c4-4edc-a478-261384bd9074)
##### For deleting and updating of blog posts, one can only perform these actions if they created the post or have "ROLE_ADMIN" authority.
##### Below shows response for users with no "ROLE_ADMIN" trying to delete blog posts they didn't create
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/9eab2223-d91e-4abb-923c-4d0f593a96b7)
##### To add comment to a blog post, specify postId as a path variable to be able to proceed, below image illustrates the same
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/14266fb1-104b-4795-9030-e02690b62d76)
##### The image below shows response after successfully posting a comment to a post
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/cab64be4-d201-4101-a412-15dcd40fd466)
##### To retrieve all comments belonging to a post, provide the necessary request parameters, default values have been provided
##### Below is an example response for such request
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/172773bb-c956-4d42-b835-5a8c25baad33)
##### To delete and update blog post comment, provide the post id and comment id for the comment you want to delete
##### N/B Only the comment poster or user with role "ROLE_ADMIN" can perform the above actions
##### Below is the endpoint for updating user profile password
##### Exceptions for this are well handled, ie the new password cannot be same to the old password, user must sign in to be able to update 
##### their password and the new password must be confirmed with same details
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/06e7cc0e-a5c6-456d-a504-77aafcafde07)
##### Below image shows the list of all endpoints I have implemented
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/63a765e7-50e6-4e0c-9aec-0ded16a22759)
##### Before running the project create an empty database with the name "creswave" or the edited db name in application.yml file
##### The db configuratons for this project are for MYSQL
##### Update the db credentials, password and username to match your local set up 
##### To run the project, clone this project by running git clone https://github.com/Philipbukki/CRESWAVE_CODE_TEST.git
##### In case of any queries or challenges while running the project, please do not hesitate to contact me via email at phil.bukki@gmail.com or by phone at +254798335550.


