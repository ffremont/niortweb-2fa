import { FunctionComponent, useState } from "react";
import { useNavigate } from "react-router-dom";

interface LoginProps {}

// @see https://github.com/SinghDigamber/react-login-signup-ui-template
const Login: FunctionComponent<LoginProps> = () => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [code, setCode] = useState("");

  const navigate = useNavigate();

  const onSubmit = (e: any) => {
    e.preventDefault();

    let formData = new FormData();
    formData.append("password", password);
    formData.append("code", code);

    fetch(`/api/users/${id}/signin`, {
      body: formData,
      method: "post",
    }).then(response =>{
      if( response.status === 204) navigate('/connected')
      else alert('Une erreur est survenue')
    });
  };

  return (
    <div className="card" style={{ width: "18rem" }}>
      <div className="card-body">
        <h5 className="card-title">Connexion</h5>
        <form onSubmit={onSubmit}>
          <div className="form-group">
            <label>Id</label>
            <input
              type="text"
              required
              value={id}
              onChange={(e) => setId(e.target.value)}
              className="form-control"
              placeholder="Enter email"
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="form-control"
              placeholder="Enter password"
            />
          </div>

          <div className="form-group">
            <label>2FA code</label>
            <input
              type="text"
              required
              value={code}
              onChange={(e) => setCode(e.target.value)}
              className="form-control"
              placeholder="000000"
            />
          </div>

          <button type="submit" className="btn btn-primary btn-block">
            Se connecter
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
