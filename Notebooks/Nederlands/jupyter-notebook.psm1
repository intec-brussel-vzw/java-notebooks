function New-NetworkPodman(){
    podman network create --driver bridge edunet
}

function New-NetworkDocker(){
    docker network create --driver bridge edunet
}

function Get-NetworkPodman(){
    podman network inspect edunet
}

function Get-NetworkDocker(){
    docker network inspect edunet
}

function New-JavaNotebookPodman() {

    podman run `
        --name jnb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/java-notebook-server:latest
        
    podman container inspect jnb
    podman logs jnb
    Get-NetworkPodman

}

function New-JavaNotebookDocker() {

    docker run `
        --name jnb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/java-notebook-server:latest
        
    docker container inspect jnb
    docker logs jnb
    Get-NetworkDocker

}

function New-DotNetNotebookPodman() {

    podman run `
        --name dnnb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/dotnet-notebook-server:latest
        
    podman container inspect dnnb
    podman logs dnnb
    Get-NetworkPodman

}

function New-DotNetNotebookDocker() {

    docker run `
        --name dnnb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/dotnet-notebook-server:latest
        
    docker container inspect dnnb
    docker logs dnnb
    Get-NetworkDocker

}

function New-JavascriptNotebookPodman(){

    podman run `
        --name jsnb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/javascript-notebook-server:latest
        
    podman container inspect jsnb
    podman logs jsnb
    Get-NetworkPodman

}

function New-JavascriptNotebookDocker(){

    docker run `
        --name jsnb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/javascript-notebook-server:latest
        
    docker container inspect jsnb
    docker logs jsnb
    Get-NetworkDocker

}

function New-PythonNotebookPodman(){

    podman run `
        --name pynb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/python-notebook-server:latest
        
    podman container inspect pynb
    podman logs pynb
    Get-NetworkPodman
}

function New-PythonNotebookDocker(){

    docker run `
        --name pynb `
        --publish '8888' `
        --env PASSWORD=intec-123 `
        --env JUPYTER_TOKEN=intec-123 `
        --detach `
        --network edunet `
        --volume ./:/home/jovyan/work/ `
        docker.io/yilmazchef/python-notebook-server:latest
        
    docker container inspect pynb
    docker logs pynb
    Get-NetworkDocker
}