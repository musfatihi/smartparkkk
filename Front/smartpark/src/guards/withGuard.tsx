import React from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/reducers/RootState';
import { getDecodedToken } from './tokenUtils';
import { Navigate } from 'react-router-dom';

interface GuardProps {
  allowedRoles: string[];
}

const withGuard = (WrappedComponent: React.ComponentType<any>) => {
  const Guard = (props: GuardProps) => {
    const { allowedRoles } = props;
    const token = useSelector((state: RootState) => state.auth.token);

    if (!token) {
      return <Navigate to="/error" replace />;
    }

    const decodedToken = getDecodedToken(token);
    const userRole = decodedToken.iss;

    if (allowedRoles.includes(userRole)) {
      return <WrappedComponent {...props} />;
    } else{
      return <Navigate to="/error" replace />;
    }
  };

  return Guard;
};

export default withGuard;