import axios from "axios";

export default function setAuthorizationToken(token) {
  if (token) {
    axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    axios.defaults.baseURL = process.env.REACT_APP_BASE_URL;
  } else {
    delete axios.defaults.headers.common["Authorization"];
  }
}
