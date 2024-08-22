# Start Pulsar with Standalone Mode

## Install Pulsar

- Java Version: `zulu-17`

Download package from: [Download Pulsar Package](https://pulsar.apache.org/download/)

Then unzip this package to any place, then config the path variable

post check: `pulsar help`

## Start Pulsar Service

```bash
pulsar standalone
```

## Pulsar Manager

- Version: `0.4.0`
- Java Version: `zulu-11`

Download from: [Download Pulsar Manager](https://dist.apache.org/repos/dist/release/pulsar/pulsar-manager/pulsar-manager-0.4.0/)

Then upzip the package, and cd to the package, you should see there's a `pulsar-manager.tar` file, unzip this file.

The web page is located to `dist`, you need to mv this folder into `pulsar-manager`, and rename it to `ui`,  
and the URL changes to `http://localhost:7750/ui/index.html`  

### 2. Start Pulsar Manager UI

> Note:
> Since the java version installed is zulu-17, so may need to export zulu-11 path with session level

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-11.jdk/Contents/Home
export PATH=$HOME/bin:$HOME/.local/bin:/usr/local/bin:$JAVA_HOME/bin:$MAVEN_HOME/bin:$ACTIVE_MQ_HOME/bin:$JMETER_HOME/bin:$GEMFIRE_HOME/bin:$PULSAR/bin:$PULSAR_MNG/bin:$PATH
java -version
```

Then start the Pulsar Manager UI

```bash
cd /Users/nickwang/Documents/application/apache-pulsar-manager-0.4.0-bin/pulsar-manager
./bin/pulsar-manager
```

### 3. Create user

- Create token
```bash
CSRF_TOKEN=$(curl http://localhost:7750/pulsar-manager/csrf-token)
```

- Create User
```bash
curl \
-H "X-XSRF-TOKEN: $CSRF_TOKEN" \
-H "Cookie: XSRF-TOKEN=$CSRF_TOKEN;" \
-H "Content-Type: application/json" \
-X PUT http://localhost:7750/pulsar-manager/users/superuser \
-d '{"name": "pulsar", "password": "pulsar", "description": "test", "email": "xx@outlook.com"}' 
```

### 4. Access Page

- Access: [Pulsar Manager UI](http://localhost:7750/ui/index.html)
- User / Password: `pulsar`

