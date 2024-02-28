from locust import HttpUser, task


class HelloWorldUser(HttpUser):
    @task
    def hello_world(self):
        self.client.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")