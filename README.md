Tag Tracking Server Java Project
================================

At Airship one of our core systems allows customers to tag their customers with bits of metadata. For instance, they might tag all their users who love Beyoncé with the metadata  `beyhive_member`. They then use that metadata to select an audience for pushes or other communications they send out, such as to announce a newly scheduled show.

We'd like you to implement a very stripped down version of that service. It doesn't need to persist its state anywhere or provide more than a single combined add/remove/get call.

While it's not a requirement, we strongly prefer the project be done in Java.


Requirements
------------

Using the standard library HTTP server (or another HTTP server library of your choosing), create a server that accepts `POST` requests to `/api/tags` on port 1917. The request bodies will be JSON with the following format:

```json
{
  "user": "<user id>",
  "add": ["tag1", "tag2"],
  "remove": ["tag3"],
  "timestamp": "2018-08-10T06:49:04.360Z"
}
```

The server should keep in memory a store of user identifiers it's seen with all tags that are currently attached to them. When a request is received the server should add any tags in the `add` field to the stored record for `user id` (creating it first if necessary), remove any tags in the `remove` field, then return a response with the resulting state of `user id`:

```json
{
  "user": "<user id>",
  "tags": ["tag1", "tag2"]
}
```  

It's not an error to attempt to remove from a user a tag that it does not currently have. If a tag  appears multiple times in either `add` or `remove`, it's equivalent to appearing once. If a tag appears in both `add` and `remove` it should be treated as if it only appeared in `remove`.

If a request cannot be parsed for any reason, the server should return an HTTP 400 error response with a JSON body:

```json
{
  "error": "Helpful message here."
}
```

Operations must be treated as having occurred at `timestamp` rather than when the request is received. Requests may be received out of order, and your server must handle that such that for any set of timestamped requests for a tag on a single user the resulting state is identical no matter what order the requests are received in.

For instance the following represent possible request orders and what state should be reported after each request.

Out of order removal between two adds:

| request  | simplified `timestamp` | result  |
|---------:|:----------------------:|:-------:|
| add("a") | 20                     | `["a"]` |
| add("a") | 10                     | `["a"]` |
| rm("a")  | 15                     | `["a"]` |


Latent add received after remove:

| request  | simplified `timestamp` | result |
|---------:|:----------------------:|:------:|
| rm("a")  | 20                     | `[]`   |
| add("a") | 10                     | `[]`   |

If a tag is added and removed in the same millisecond, it should be treated as removed. Requests for a single user may overlap in time and tags updated, and they should be able to proceed in parallel with minimal coordination. If two requests `RequestA` and `RequestZ` for the same user are being processed concurrently, the response to the client issuing `RequestA` must reflect all the changes made in `RequestA`, but may reflect all,  none, or any subset of the changes contained in `RequestZ`.

Your server should be able to handle at least 100 concurrent requests. It should continue running and serving traffic until it's sent an interrupt signal (Ctrl-C), and then shut down as quickly and cleanly as possible. Using `Runtime.getRuntime().addShutdownHook()` is sufficient.


Testing
-------

Add any automated testing you feel is necessary, and let us know what manual testing you tried. At a minimum you should be able to do something like the following from your terminal:

```
curl localhost:1917/api/tags -d '{
	"user": "my_user",
	"add": ["beyhive_member"],
	"remove": [],
	"timestamp": "2018-09-07T04:32:20.786Z"
}'
```

And get back

```
{"user":"my_user","tags":["beyhive_member"]}
```


## Usage

### Build

```Bash
mvn clean package
```

### Run

Navigate to the desired module: DEX REST APP (which contains the web module)

```Bash
mvn spring-boot:run
```
