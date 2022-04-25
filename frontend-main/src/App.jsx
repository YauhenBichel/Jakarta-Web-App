import { createContext, useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { AuthenticatedApp } from './components/AuthenticatedApp';
import { UnauthenticatedApp } from './components/UnauthenticatedApp';

function App() {
	const user = true;

	return (
		<div>
			{user ? (
				<div>
					<AuthenticatedApp />
				</div>
			) : (
				<div>
					<UnauthenticatedApp />
				</div>
			)}
		</div>
	);
}

export default App;
