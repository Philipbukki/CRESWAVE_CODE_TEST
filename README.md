#### Access the application using the link: http://localhost:8080/swagger-ui/index.html#/
#### Users are created with default role of "ROLE_USER"
#### To add "ROLE_ADMIN" for testing purposes, after running the application, insert one in roles table, with id 2 and name "ROLE_ADMIN" 
#### additionaly add one record with an existing user Id i.e 1 and role id of 2 in user_roles table.
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/f140cafd-f4ce-451f-aba3-f14101b27ac8)
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/16582bfa-196c-4af1-8d51-c5dc4c182cf4)
#### Start by creating the user using the register endpoint and then use login endpoint to login to the app.
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/d94db8d1-23be-43ee-8286-022c5c732c31)
### Expected out put incase of successful login/SignIn
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/877e2a90-03a4-40f3-97cb-f4b92d1680f0)
#### Illustrates how to create Create a new Blog Post 
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/42e773e6-d5b7-4a74-b967-ea236fec49b8)
#### Expected ouput with successful post request for the blog post
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/27826246-a93c-4821-97ba-dd0e3bdd8e16)
#### Endpoint to get all blog posts
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/8a0896d4-f109-4b31-a8e3-3fd29ecbaee4)
#### Expected results 
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/054d8efd-61d3-424c-bc74-71628a2f83aa)
#### Endpoint to delete a post and expected result on successful request, this can only be done by admin user or the post creator
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/bc0561b9-44ad-42d7-9535-5d71813057b8)
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/8bb9748e-815c-4c3f-ac54-2b485218bf8b)
#### Endpoint to update a post, this can only be done by admin user or the post creator
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/55699d7b-e7b6-482c-ae2f-840ca388bcde)
#### Response when the user is not an admin or did not create the post
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/8b6546a2-8d66-4dfd-82cb-0d119dbf5678)
#### To test comment endpoints, follow the guidlines in the swagger documentation for such endpoints I have implemented
#### Role based access controll is also implemented for these endpoints
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/51c01093-af35-41e0-ae1f-3066787aa0f7)
#### Below is the endpoint for updating user profile
![image](https://github.com/Philipbukki/CRESWAVE_CODE_TEST/assets/43266759/c55f1fd7-5ab0-406b-8eb8-ccc55b9157fc)
#### To run the project, clone this project and edit the application.yml file for the db name and db credentials
#### Incase you of any queries, reach out to me on email phil.bukki@gmail.com or on +254798335550

