import { atom, useAtom } from 'jotai';
import { AuthenticatedApp } from './components/AuthenticatedApp';
import { UnauthenticatedApp } from './components/UnauthenticatedApp';

export const loggedIn = atom(false);

function App() {
	const [logged, setLogged] = useAtom(loggedIn);

	return (
		<div>
			{logged ? (
				<div>
					<AuthenticatedApp />
				</
				div>
			) : (
				<div>
					<UnauthenticatedApp />
				</div>
			)}
		</div>
	);
}

export default App;
