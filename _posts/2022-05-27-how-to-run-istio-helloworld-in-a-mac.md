1.  ``` minikube start --memory=7851 --cpus=4 --kubernetes-version=v1.23.3 
        --extra-config=controller-manager.cluster-signing-cert-file='/var/lib/minikube/certs/ca.crt'
        --extra-config=controller-manager.cluster-signing-key-file='/var/lib/minikube/certs/ca.key'
    ```

2. ``` istioctl install --set profile=demo -y ```

3. ``` kubectl label namespace default istio-injection=enabled ```

2. ``` kubectl apply -f samples/helloworld/helloworld.yaml ```

2. ``` kubectl apply -f samples/helloworld/helloworld-gateway.yaml ```

2. ``` kubectl get pods ```

2. ``` kubectl get svc istio-ingressgateway -n istio-system ```

8.  Run in another terminal ``` minikube tunnel ```

2. ```  curl localhost/hello ```
    
References:
1. [Istio getting started](https://istio.io/latest/docs/setup/getting-started/)
2. [Setting up istio on minikube](https://medium.com/@jobinesh/setting-up-istio-on-minikube-for-running-bookinfo-demo-application-af25dab2a732)
3. [Setup istio ingress](https://medium.com/codex/setup-istio-ingress-traffic-management-on-minikube-725c5e6d767a)
4. [Istio routing](https://istio.io/latest/docs/tasks/traffic-management/request-routing/)

