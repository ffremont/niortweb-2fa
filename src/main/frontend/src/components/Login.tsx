import { FunctionComponent } from "react";

interface LoginProps {}

// @see https://github.com/SinghDigamber/react-login-signup-ui-template
const Login: FunctionComponent<LoginProps> = () => {
  return (
    <div className="card" style={{width: '18rem'}}>
      <div className="card-body">
        <h5 className="card-title">Connexion</h5>
        <form>
          <div className="form-group">
            <label>Email address</label>
            <input
              type="email"
              required
              className="form-control"
              placeholder="Enter email"
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              required
              className="form-control"
              placeholder="Enter password"
            />
          </div>

          <div className="form-group">
            <label>2FA code</label>
            <input
              type="password"
              required
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
