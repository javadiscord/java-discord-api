# Imposter Mocks
[Imposter](https://www.imposter.sh/) stands out as a powerful API mocking library, simplifying the process of creating stubs for APIs. 
By leveraging either an OpenAPI spec or crafting scenarios, Imposter empowers developers to simulate API behavior effortlessly.

## Leveraging Discord's OpenAPI Spec
Within this repository, we use the capabilities of Imposter by utilizing Discord's OpenAPI spec to automatically generate test endpoints. 
These endpoints serve as assets for composing integration tests for our bot.

## Getting Started

### Installation of Imposter
* Download [Imposter Mocks](https://docs.imposter.sh/run_imposter_cli/) from their website.
  * On Mac/Linux systems, execute the following cURL command:
      ```bash
      curl -L https://raw.githubusercontent.com/gatehill/imposter-cli/main/install/install_imposter.sh | bash -
      ```
  * If you have Homebrew:
    ```bash
    brew tap gatehill/imposter
    brew install imposter
    ```

### Run Mock Server
Navigate into the /mocks directory and execute:
```bash
imposter up
```

This will start a mock server at `http://localhost:8080`

Test everything is working by using a REST client such as Postman and make a call to `http://localhost:8080//api/v10/oauth2/applications/@me`. 
You should see: 
```json
{
  "id": "",
  "name": "",
  "icon": "",
  "description": "",
  "cover_image": "",
  "slug": "",
  "bot_public": false,
  "bot_require_code_grant": false,
  "terms_of_service_url": "",
  "privacy_policy_url": "",
  "custom_install_url": "",
  "verify_key": "",
  "flags": 0,
  "max_participants": 0,
  "interactions_endpoint_url": "",
  "role_connections_verification_url": "",
  "owner": {
    "id": "",
    "username": "",
    "avatar": "",
    "discriminator": "",
    "public_flags": 0,
    "flags": 0,
    "bot": false,
    "system": false,
    "banner": "",
    "accent_color": 0,
    "global_name": ""
  },
  "approximate_guild_count": 0
}
```