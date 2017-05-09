import requests

url = "https://ps.perfectomobile.com/services/repositories/media/PRIVATE:applications/Recipes.ipa"

querystring = {"operation":"upload","user":"lees@perfectomobile.com","password":"Welcome1701"}

payload = "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"\"; filename=\"Recipes.ipa\"\r\nContent-Type: false\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--"
headers = {
    'content-type': "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW",
    'cache-control': "no-cache",
    'postman-token': "ddbe25eb-b6f9-8a71-aaf6-4d841d9bea88"
    }

response = requests.request("POST", url, data=payload, headers=headers, params=querystring)

print(response.text)