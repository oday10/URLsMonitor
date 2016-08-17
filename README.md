# URLsMonitor
Monitoring for URLs.

This project meant to monitor some important public files/pages to make sure their content doesn't change (unless we meant to).

The project reads a list of URLs (http or https) from a configuration file.
The configuration file has pairs of URL+IP, where IP can be * (which means perform normal DNS query to obtain the IP).

An example for a configuration file:

http://x.y.com/file1.txt   10.0.0.1
http://x.y.com/file1.txt   10.0.0.2
http://x.y.com/file1.txt   10.0.0.3
http://x.y.com/file2.txt   10.0.0.1
https://r.t.com/file2.txt   10.0.0.1
http://r.t.com/file2.txt   10.0.0.2
https://r.t.com/file2.txt   10.0.0.2
http://r.t.com/file3.txt   *
https://r.t.com/file3.txt   *
http://r.t.com/file4.txt   *
https://r.t.com/file4.txt   *


For each URL, the project downloads the page and calculate a SHA1 hash of the HTTP response body.
Whenever the hash value changes, an email alert is sent to the administrator, the email includes the affected URL and IP.

The check should occurs once for every 10 minutes.
