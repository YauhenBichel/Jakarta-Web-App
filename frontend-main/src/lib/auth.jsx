import axios from 'axios';
import { createContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const Auth = ({children}) => {
  const [user, setUser] = useState(null)

  useEffect(() => {
    axios.post('http://localhost:8080/holidaysystem/api/employee', {
      email: 'admin.admin@holidaysystem.org',
      password: '1234'
    }).then((resp) => {
      console.log(resp)
    }).then((error) => {
      console.log(error)
    })
  }, [])

  return(
    <AuthContext.Provider value={user}>
      {children}
    </AuthContext.Provider>
  )
}