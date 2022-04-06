export function RequestExample() {
	return (
		<div className='py-12'>
			<div className='max-w-4xl mx-auto bg-white shadow overflow-hidden sm:rounded-lg'>
				<div className='px-4 py-5 sm:px-6'>
					<h3 className='text-lg leading-6 font-medium text-gray-900'>
						Holiday Request
					</h3>
				</div>
				<div className='border-t border-gray-200 px-4 py-5 sm:p-0'>
					<dl className='sm:divide-y sm:divide-gray-200'>
						<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
							<dt className='text-sm font-medium text-gray-500'>Full name</dt>
							<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
								Margot Foster
							</dd>
						</div>
						<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
							<dt className='text-sm font-medium text-gray-500'>
								Application from
							</dt>
							<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
								16 Feb. 2022
							</dd>
						</div>
						<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
							<dt className='text-sm font-medium text-gray-500'>to</dt>
							<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
								22 Feb. 2022
							</dd>
						</div>
						<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
							<dt className='text-sm font-medium text-gray-500'>Constraints</dt>
							<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
								<ul className='space-y-2 list-disc'>
									<li>Peak Season</li>
									<li>Staff Shortage On Site</li>
									<li>At least 1 Head/Deputy has to be On Site</li>
								</ul>
							</dd>
						</div>
						<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
							<dt className='text-sm font-medium text-gray-500'>
								Suggested Dates
							</dt>
							<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
								<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
									01 Mar. 2022
								</dd>
							</dd>
						</div>
						<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
							<dt className='text-sm font-medium text-gray-500'>Decision</dt>
							<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
								<div className='flex items-center space-x-4'>
									<button
										type='button'
										className='relative inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-green-600 shadow-sm hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500'>
										<span>Accept Request</span>
									</button>
									<button
										type='button'
										className='relative inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500'>
										<span>Deny Request</span>
									</button>
								</div>
							</dd>
						</div>
					</dl>
				</div>
			</div>
		</div>
	);
}
