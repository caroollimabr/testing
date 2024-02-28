from locust import task, run_single_user
from locust import FastHttpUser


class opensource_demo_orangehrmlive_com(FastHttpUser):
    host = "https://opensource-demo.orangehrmlive.com"
    default_headers = {
        "Accept-Encoding": "gzip, deflate, br, zstd",
        "Accept-Language": "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7",
        "Connection": "keep-alive",
        "Host": "opensource-demo.orangehrmlive.com",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
        "sec-ch-ua": '"Chromium";v="122", "Not(A:Brand";v="24", "Google Chrome";v="122"',
        "sec-ch-ua-mobile": "?0",
        "sec-ch-ua-platform": '"Windows"',
    }

    @task
    def t(self):
        with self.client.request(
            "POST",
            "/web/index.php/auth/validate",
            headers={
                "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
                "Cache-Control": "max-age=0",
                "Content-Length": "163",
                "Content-Type": "application/x-www-form-urlencoded",
                "Cookie": "orangehrm=020dc3eecf5095cf8aae91308931caa5",
                "Origin": "https://opensource-demo.orangehrmlive.com",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login",
                "Sec-Fetch-Dest": "document",
                "Sec-Fetch-Mode": "navigate",
                "Sec-Fetch-User": "?1",
                "Upgrade-Insecure-Requests": "1",
            },
            data="_token=db05c24ce8ca3002bb51.82MHMlGlLIy-IOf-nk6jJ8vLefw7DlYKIzIOz-YEwq4.xCxoATXDf-jzZNKu8R3OUfyqS5dYYDpEFGRLqq9djOCsEUBHO8sftdZYiA&username=Admin&password=admin123",
            catch_response=True,
        ) as resp:
            pass
        with self.client.request(
            "GET",
            "/web/index.php/dashboard/index",
            headers={
                "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
                "Cache-Control": "max-age=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login",
                "Sec-Fetch-Dest": "document",
                "Sec-Fetch-Mode": "navigate",
                "Sec-Fetch-User": "?1",
                "Upgrade-Insecure-Requests": "1",
            },
            catch_response=True,
        ) as resp:
            pass
        with self.client.request(
            "GET",
            "/web/index.php/core/i18n/messages",
            headers={
                "Accept": "application/json",
                "Cache-Control": "max-age=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "If-None-Match": '"r2bCuSoCq/RIhAIrdHucIA2NuLVeuc+DC+DURIjsY5E="',
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
                "contentType": "application/json",
            },
            catch_response=True,
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/dashboard/employees/time-at-work?timezoneOffset=-3&currentDate=2024-02-27&currentTime=21:19",
            headers={
                "Accept": "application/json",
                "Cache-Control": "no-store, no-cache, must-revalidate, post-check=0, pre-check=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/dashboard/employees/action-summary",
            headers={
                "Accept": "application/json",
                "Cache-Control": "no-store, no-cache, must-revalidate, post-check=0, pre-check=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/dashboard/shortcuts",
            headers={
                "Accept": "application/json",
                "Cache-Control": "no-store, no-cache, must-revalidate, post-check=0, pre-check=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/buzz/feed?limit=5&offset=0&sortOrder=DESC&sortField=share.createdAtUtc",
            headers={
                "Accept": "application/json, text/plain, */*",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/dashboard/employees/leaves?date=2024-02-27",
            headers={
                "Accept": "application/json",
                "Cache-Control": "no-store, no-cache, must-revalidate, post-check=0, pre-check=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/dashboard/employees/subunit",
            headers={
                "Accept": "application/json",
                "Cache-Control": "no-store, no-cache, must-revalidate, post-check=0, pre-check=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.rest(
            "GET",
            "/web/index.php/api/v2/dashboard/employees/locations",
            headers={
                "Accept": "application/json",
                "Cache-Control": "no-store, no-cache, must-revalidate, post-check=0, pre-check=0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
        ) as resp:
            pass
        with self.client.request(
            "POST",
            "/web/index.php/events/push",
            headers={
                "Accept": "application/json",
                "Content-Length": "0",
                "Cookie": "orangehrm=73fa1b76b93e0bf3da5196a04fd7fb6a",
                "Origin": "https://opensource-demo.orangehrmlive.com",
                "Referer": "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",
                "Sec-Fetch-Dest": "empty",
                "Sec-Fetch-Mode": "cors",
            },
            catch_response=True,
        ) as resp:
            pass


if __name__ == "__main__":
    run_single_user(opensource_demo_orangehrmlive_com)
