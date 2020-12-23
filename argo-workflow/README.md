
https://argoproj.github.io/argo/quick-start/

kubectl create ns argo

kubectl apply -n argo -f https://raw.githubusercontent.com/argoproj/argo/stable/manifests/quick-start-postgres.yaml

argo submit -n argo --watch https://raw.githubusercontent.com/argoproj/argo/master/examples/hello-world.yaml

argo list -n argo
argo get -n argo @latest
argo logs -n argo @latest


To summarize, workflow specs are composed of a set of Argo templates where each template consists of an optional input section, an optional output section and either a container invocation or a list of steps where each step invokes another template.

1. import data
2. split data
3. build model
4. train model
5. Predict
6. accuracy - cost function
