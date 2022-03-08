import { FunctionComponent, useEffect } from "react";

interface ConnectedProps {
    
}
 
const Connected: FunctionComponent<ConnectedProps> = () => {

    useEffect(() => {
        fetch('/api/users/me')
        .then(resp => resp.text())
        .then(txt => console.log(txt))
    }, []);

    return (<div>🥳 vous êtes connecté</div>  );
}
 
export default Connected;