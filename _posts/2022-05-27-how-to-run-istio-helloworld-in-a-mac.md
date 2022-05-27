1.  ```bash minikube start --memory=7851 --cpus=4 --kubernetes-version=v1.23.3 \
    --extra-config=controller-manager.cluster-signing-cert-file="/var/lib/minikube/certs/ca.crt" \
    --extra-config=controller-manager.cluster-signing-key-file="/var/lib/minikube/certs/ca.key" ```
    
    
2. ```bash istioctl install --set profile=demo -y ```
    
3. ```bash kubectl apply -f samples/helloworld/helloworld.yaml ```

4. ```bash kubectl apply -f samples/helloworld/helloworld-gateway.yaml ```

5. ```bash kubectl get pods ```

6. ```bash kubectl get svc istio-ingressgateway -n istio-system ```

7. ```bash minikube tunnel ```

8. ```bash curl localhost/hello ```
    
References:
1. [Isto getting started](https://istio.io/latest/docs/setup/getting-started/)
2. [Setting up istio on minikube](https://medium.com/@jobinesh/setting-up-istio-on-minikube-for-running-bookinfo-demo-application-af25dab2a732)
3. [Setup isto ingress](https://medium.com/codex/setup-istio-ingress-traffic-management-on-minikube-725c5e6d767a)

